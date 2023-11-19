
setwd("C:/Users/burdo/Desktop/Git/Uni/Einfuhrung KI")
Daten <- read.csv2("Data/Maschinendaten.csv", header = TRUE, sep = ";", fill = TRUE, stringsAsFactors = TRUE)

Daten[,"Werkzeug"] <- as.factor(Daten[,"Werkzeug"])
Daten[,"Ausschuss"] <- as.factor(Daten[,"Ausschuss"])

summary(Daten)
model <- lm(Qualitaetsparameter ~ Werkzeug + Maschine + Vorschub, data = Daten)

model

actual <- Daten[,"Qualitaetsparameter"]
Prognosen <- model$fitted.values
Prognosefehler <- mean(abs(actual - Prognosen))
Prognosefehler

modelIntercept <- model$coefficients[1]
werkzeug3 <- model$coefficients[3]
machineA <- 0
vorschub <- model$coefficients[5]

prognose_3_A_2p5 <- modelIntercept + werkzeug3 + machineA + (2.5 * vorschub)

prognose_3_A_2p5