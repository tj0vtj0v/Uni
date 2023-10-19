
setwd("C:/Users/burdo/Desktop/Git/Uni/Einfuhrung KI")

data <- read.csv2("Data/Koerpergewicht.csv", header = TRUE, sep = ";", fill = TRUE, stringsAsFactors = TRUE)

data

summary(data)

#data[2,2]

#data[2,]

#data[,2]

#data[,"Alter"]