

#################################################################
# Lineare Regression 
#################################################################
# von Prof. Dr. Robert Hable, Technische Hochschule Deggendorf
#################################################################



######################################
# Beispiel f�r Regression
#############


# Setzen des Pfades und Einlesen der Beispiel-Daten

  setwd("C:/Users/burdo/Desktop/Git/Uni/Einfuhrung KI")
  Daten <- read.csv2("Data/Koerpergewicht_2.csv",header=TRUE,sep=";",fill=TRUE,stringsAsFactors=TRUE)
  Daten[1:5,]

# Kontrolle der Datentypen und Ausgabe der Summary.r
  
  summary(Daten)


# Berechnung der Regression

  model <- lm( Gewicht ~ Geschlecht + Alter + Groesse, data=Daten)
  model

# Berechnung des mittleren Prognosefehlers (MAE)

  y <- Daten[,"Gewicht"] 
  Prognosen <- model$fitted.values 
  Prognosefehler <- mean( abs( y - Prognosen ) )
  Prognosefehler
  
  
#####  
# Beispiel f�r einfache Regression (nur eine Einflussvariablen)

  model.2 <- lm( Gewicht ~ Groesse, data=Daten)
  model.2 

  # Zeichnen der Punktewolke mit Regressionsfunktion
  
    # Zun�chst die Punktewolke
    
      x <- Daten[,"Groesse"]
      y <- Daten[,"Gewicht"]
      plot(x,y,pch=19,main="Zusammenhang Groesse und Gewicht")
    
    # Die Regressionsgerade l�sst sich folgenderma�en zeichnen
    # (nur m�glich bei nur einer Einflussvariablen)
    
      Parameter <- model.2$coefficients
      Parameter
      
      Intercept <- Parameter[1]
      Steigung <- Parameter[2]
      
      abline(a=Intercept,b=Steigung) 





  
  
  
    
