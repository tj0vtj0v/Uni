
# Setzen des Pfades und Einlesen der Daten

  setwd("Meine-Mietspiegel-App")
  
  Daten <- read.csv("Mietspiegel.csv",header=TRUE,sep=";",fill=TRUE,stringsAsFactors=TRUE)

  Daten[,"bad"] <- as.factor(Daten[,"bad"])
  Daten[,"kueche"] <- as.factor(Daten[,"kueche"])
  Daten[,"lage"] <- as.factor(Daten[,"lage"])
  Daten[,"zh"] <- as.factor(Daten[,"zh"])

# Berechnung der linearen Regression

  model <- lm( mieteqm ~ flaeche + bjahr + bad + kueche + lage + zh, data=Daten)


# Starten der Shiny-App

  library(shiny)

  runApp("App-Mietspiegel")

