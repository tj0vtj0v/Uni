

#################################################################
# Maschinelles Lernen mit Entscheidungsb�umen
#################################################################
# von Dr. habil. Robert Hable, Technische Hochschule Deggendorf
#################################################################


# F�r maschinelles Lernen muss erst ein Zusatzpaket in R installiert werden
# Wir verwenden im folgenden das Paket "tree"
# Pakete muss man (einmalig) mit folgendem Befehl installieren
# (Internetverbindung n�tig!):

# install.packages("tree")

# Damit wird das Paket auf dem eigenen Computer installiert. Immer wenn
# man das Paket verwenden will, muss man es aber in der aktuellen
# R-Sitzung aus der Bibliothek laden. Das geht folgenderma�en:

  library(tree)



######################################
# Regression mit Entscheidungsb�umen
#############



###
# Wir verwenden wieder unsere Autodaten als Beispieldaten:

  # Setzen des Pfades und Einlesen der Beispiel-Daten

    setwd("C:/Users/rhable.AIKIW10NB01/Nextcloud2/Lehre/Daten/Einfuehrung-KI")
    Daten <- read.csv("autos.csv",header=TRUE,sep=",",fill=TRUE,stringsAsFactors=TRUE)
    Daten[1:10,]

  # Kontrolle der Datentypen durch Ausgabe der Summary.r
  
    summary(Daten)
    Daten[,"Herkunft"] <- as.factor(Daten[,"Herkunft"])
    summary(Daten)


  # Berechnung des Modells

    Baum <- tree(Verbrauch ~ Zylinder + Hubraum + Leistung + Gewicht + Beschleunigung + Baujahr + Herkunft, data=Daten)
    plot(Baum)
    text(Baum)
    
    tuning <- cv.tree(Baum, K=5)

    tuning
    
    plot(tuning)

  # Der Baum mit der optimalen Anzahl an Endknoten:
  
    t <- which.min(tuning$dev)
    Anzahl.Endknoten <- tuning$size[t]

    model <- prune.tree(Baum,best=Anzahl.Endknoten)
    plot(model)
    text(model)

  # Berechnen von Prognosen

    # Berechnung der Prognosen f�r die Personen aus dem Datensatz:
    
      # Auswahl aller Input-Variablen und Speicherung unter 'X'
        X <- Daten[,c("Zylinder","Hubraum","Leistung","Gewicht","Beschleunigung","Baujahr","Herkunft")]

      # Berechnung der Prognoseergebnisse:
        predict(model,X)
      
    # Berechnung einer Prognose f�r einen neuen Datenpunkt
	# Achtung: Umwandlung in factor mit as.factor, wo n�tig!
    
      x.neu <- data.frame(4,200,120,1500,17,1975,as.factor(2))
      names(x.neu) <- names(X)
      predict(model,x.neu)


################
# Nun wiederholen wir die Berechnung nochmal, wobei wir den Datensatz
# vorher in Test- und Trainingsdaten aufteilen:

# Aufteilen der Daten:
  # Anzahl der Daten
    length(Daten[,1])
  # Da es 391 Datenpunkte sind, werden bei einer (70% zu 30% Aufteilung)
  # 274 Datenpunkte als Trainingsdaten gew�hlt und die restlichen Datenpunkte als
  # Testdaten
    Daten.train <- Daten[1:274,]
    Daten.test <- Daten[275:391,]

  # Berechnung des Modells auf den Trainingsdaten:
  # Achtung: im Befehl 'tree' steht bei 'data' nun Daten.train !!!

    Baum <- tree(Verbrauch ~ Zylinder + Hubraum + Leistung + Gewicht + Beschleunigung + Baujahr + Herkunft, data=Daten.train)
    tuning <- cv.tree(Baum, K=5)
    t <- which.min(tuning$dev)
    Anzahl.Endknoten <- tuning$size[t]

    model <- prune.tree(Baum,best=Anzahl.Endknoten)
    plot(model)
    text(model)


  # Berechnung der Prognoseergebnisse auf den Testdaten:

    X.test <- Daten.test[,c("Zylinder","Hubraum","Leistung","Gewicht","Beschleunigung","Baujahr","Herkunft")]
    prognosen <- predict(model,X.test)

  # Berechnung des mittleren Prognosefehlers (MAE)

    y.test <- Daten.test[,"Verbrauch"]
    mean(abs(y.test-prognosen))





######################################
# Klassifikation mit Entscheidungsb�umen
#############

    setwd("C:/Users/rhable.AIKIW10NB01/Nextcloud2/Lehre/Daten/Einfuehrung-KI")
    Daten <- read.csv("Kreditscoring.csv",header=TRUE,sep=";",fill=TRUE)

# Ausgabe der ersten 10 Datenpunkte

  Daten[1:10,]


# Kontrolle der Datentypen �ber den summary-Befehl

  summary(Daten)
  
  Daten[,"V21"] <- as.factor(Daten[,"V21"])
  
  summary(Daten)

  # Berechnung des Modells mit ausgew�hlten Einflussvariablen

    Baum <- tree(V21 ~ V2 + V3 + V4 + V5, data=Daten)
    tuning <- cv.tree(Baum, K=10)
    plot(tuning)

  # Der Baum mit der optimalen Anzahl an Endknoten:
  
    t <- which.min(tuning$dev)
    Anzahl.Endknoten <- tuning$size[t]
    Anzahl.Endknoten
    
    model <- prune.tree(Baum,best=Anzahl.Endknoten)
    plot(model)
    text(model)

  # Berechnen von Prognosen

    # Berechnung der Prognosen f�r die Personen aus dem Datensatz:
    
      # Auswahl aller Input-Variablen und Speicherung unter 'X'
        X <- Daten[,c("V2","V3","V4","V5")]

      # Berechnung der Prognoseergebnisse:
        predict(model,X)


  # Berechnung der Prognoseg�te:
  
    A <- matrix(0,ncol=2,nrow=2)
    colnames(A) <- c("Real: positiv", "Real: negativ") 
    rownames(A) <- c("Prognose: positiv", "Prognose: negativ") 

    prognosen <- predict(model,X)
    prognosen
    
    # Umrechnen der Wahrscheinlichkeiten in 0/1-Prognosen
      prognosen <- round(prognosen[,2])
    
    y <- Daten[,"V21"]
    
    A[1,1] <- sum(ifelse(y == 0 & prognosen == 0, 1,0))
    A[1,2] <- sum(ifelse(y == 1 & prognosen == 0, 1,0))
    A[2,1] <- sum(ifelse(y == 0 & prognosen == 1, 1,0))
    A[2,2] <- sum(ifelse(y == 1 & prognosen == 1, 1,0))

    A











