setwd("G:/Uni/Einfuhrung KI")

data <- read.csv("My_Try/my_data.csv", header = TRUE, sep = ",", fill = TRUE, stringsAsFactors = TRUE)

data[,"YEAR"] <- as.factor(data[,"YEAR"])
data[,"MONTH"] <- as.factor(data[,"MONTH"])

summary(data)
data[1:5,]

model <- lm(amount ~ YEAR + MONTH + DAY, data=data)
model


Prognosen <- model$fitted.values
Prognosefehler <- mean(abs(y - Prognosen))
Prognosefehler

