setwd("C:/Users/burdo/Desktop/Git/Uni/Einfuhrung KI")

data <- read.csv2("Data/FreierFall.csv", header = TRUE, sep = ";", fill = TRUE, stringsAsFactors = TRUE)

writeLines("\n\nSummary of read data")
summary(data)

writeLines("\nFirst 12 rows")
data[1:12, ]

writeLines("\nPlot gets created...")
plot(data[, "Zeit"], data[, "Position"],
  main="position over time",
  xlab="time",
  ylab="position",
  #xlim=c(1.5,2),
  #ylim=c(50,100),
  col="blue",
  pch=16)

writeLines("\nmean over position")
mean(data[, "Position"])

writeLines("\nstandard abbreviation over position")
sd(data[, "Position"])