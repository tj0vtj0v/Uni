
setwd("C:/Users/burdo/Desktop/Git/Uni/Einfuhrung KI")

data <- read.csv2("Data/Koerpergewicht.csv", header = TRUE, sep = ";", fill = TRUE, stringsAsFactors = TRUE)

#data

#summary(data)

#data[2,2]

#data[2,]

#data[,2]

#data[,"Alter"]

#gr <- data[,"Groesse"]

#sd(gr)

#hist(gr)

#mean(gr)

#print("normal print")
#print("normal print without quotes", quote = FALSE)
#writeLines("pretty print")

data <- read.csv2("Data/Koerpergewicht.csv", header = TRUE, sep = ";", fill = TRUE, stringsAsFactors = TRUE)

#summary(data)

#plot(data[, "Groesse"], data[, "Gewicht"]) # plot data

x <- data[, "Groesse"]
y <- data[, "Gewicht"]
plot(x, y,
  main="My Title",
  xlab="size",
  ylab="weight",
  xlim=c(1.5,2),
  ylim=c(50,100),
  col="blue",
  pch=19) # plot data with adjusted flags

myarr <- c(1, 2, 3, 4)
myarr