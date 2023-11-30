
setwd("C:/Users/burdo/Desktop/Git/Uni/Einfuhrung KI")
Daten <- read.csv2("Data/Maschinendaten.csv", header = TRUE, sep = ";", fill = TRUE, stringsAsFactors = TRUE)

Daten[,"Werkzeug"] <- as.factor(Daten[,"Werkzeug"])
Daten[,"Ausschuss"] <- as.factor(Daten[,"Ausschuss"])

summary(Daten)

model <- glm(Ausschuss ~ Werkzeug + Maschine + Vorschub, data = Daten, binomial(link = "logit"))
model

Prognosen <- round(model$fitted.values)
Prognosefehler <- mean(ifelse(Daten[, "Ausschuss"] != Prognosen, 1, 0))

Prognosefehler

newdata <- data.frame(Werkzeug=as.factor(3), Maschine="A", Vorschub=2.5)

predict.glm(model, newdata=newdata, type="response")
