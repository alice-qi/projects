install.packages("ISLR")
install.packages("tree") 
install.packages(c("tidyverse", "sqldf", "RSQLite"))

library(ISLR)
library(tree)
library(readxl)
library(dplyr)
library(ggplot2)

# reading in the dataset into bio_data variable
bio_data <- read_excel("/Users/alice/Documents/School 2020-21/COMM 492/BiomarkerT3.xlsx")
# alternatively, you can use: 
# bio_data <- read_excel(file.choose())

str(bio_data)
# use summary statistics to find variables with potential extreme outliers
summary(bio_data)
names(bio_data)
# visualize the outliers using a boxplot on select variables
ggplot(bio_data, aes(x=Biomarker9, y="")) +
  geom_boxplot() +
  ggtitle("Box Plot")
ggplot(bio_data, aes(x=Biomarker10, y="")) +
  geom_boxplot() +
  ggtitle("Box Plot")
ggplot(bio_data, aes(x=Biomarker17, y="")) +
  geom_boxplot() +
  ggtitle("Box Plot")

# transforming Diagnosis from chr into a discrete binary variable
# if Diagnosis is "Healthy", then the value will be Yes, else, it will be No ("Acute" or "Chronic")
Sick = ifelse(bio_data$Diagnosis=="Healthy", "No", "Yes")

# add this new variable, Sick, to the bio_data dataframe
bio_data = data.frame(bio_data, Sick)
str(bio_data)

# used to convert Sick from chr to a factor
bio_data$Sick = as.factor(bio_data$Sick)
str(bio_data)

# I've omitted the Diagnosis variable for the prediction since Sick variable was created from it
tree.bio_data = tree(Sick~.-Diagnosis, data=bio_data)
# summary shows us the variables involved, number of terminal nodes, residual mean difference, and accuracy
summary(tree.bio_data) 

plot(tree.bio_data)
text(tree.bio_data, pretty = 0)
tree.bio_data # shows detailed summary of the tree printed to console

set.seed(101)
# splitting data evenly into a training set of 500 and test set of 500
train=sample(1:nrow(bio_data),500)
bio_data.test = bio_data[-train,]
Sick.test = Sick[-train]

tree.bio_data = tree(Sick~.-Diagnosis, bio_data, subset=train)
plot(tree.bio_data)
text(tree.bio_data, pretty=0) 

tree.pred = predict(tree.bio_data, bio_data[-train,], type="class")
with(bio_data[-train,], table(tree.pred, Sick))
(139+307)/500 # accuracy is 89.2% 

cv.bio_data = cv.tree(tree.bio_data, FUN = prune.misclass)
cv.bio_data
plot(cv.bio_data) 
# very large drop and then subsequent decline in the misclasification rate as trees are pruned smaller and smaller
# I will pick somewhere in the very low misclassification rate; trying size of 6-10

prune.bio_data = prune.misclass(tree.bio_data, best = 6)
plot(prune.bio_data)
text(prune.bio_data, pretty=0) 

tree.pred = predict(prune.bio_data, bio_data[-train,], type="class") 
with(bio_data[-train,], table(tree.pred, Sick))
(144+318)/500 # 92.4% accuracy, best=6, seed(101), this is the best performing model
(148+313)/500 # 92.2% accuracy, best=8, seed(101)
(150+309)/500 # 91.8% accuracy, best=10, seed(101)

######################################################################################################
# MODEL 2

# Now I'm going to try to only get the rows in the data set for patients diagnosed with acute or chronic illness
# Using the dplyr package to filter for only the rows where Sick is Yes
# bio_data_sick now contains all the data only for sick patients
bio_data_sick <- filter(bio_data, Sick=="Yes") 
str(bio_data_sick)

# Removing the Sick column to replace with the Acute disease column
bio_data_sick <- select(bio_data_sick, Biomarker1:Diagnosis)

# if Diagnosis is "Acute", then the value will be Yes, else, it will be No 
Acute = ifelse(bio_data_sick$Diagnosis=="Chronic", "No", "Yes")

# add this new variable, Acute, to the bio_data dataframe
bio_data_sick = data.frame(bio_data_sick, Acute)
str(bio_data_sick)

# used to convert Sick from chr to a factor
bio_data_sick$Acute = as.factor(bio_data_sick$Acute)
str(bio_data_sick)

# omitted the Diagnosis variable for the prediction since Sick variable was created from it
tree.bio_data_sick = tree(Acute~.-Diagnosis, data=bio_data_sick)
# summary shows us the variables involved, number of terminal nodes, residual mean difference, and accuracy
summary(tree.bio_data_sick) 

plot(tree.bio_data_sick)
text(tree.bio_data_sick, pretty = 0)
tree.bio_data_sick

set.seed(102)
# splitting data into a training set of 400 and test set of 265
train=sample(1:nrow(bio_data_sick),400)
bio_data_sick.test = bio_data_sick[-train,]
Acute.test = Acute[-train]

tree.bio_data_sick = tree(Acute~.-Diagnosis, bio_data_sick, subset=train)
plot(tree.bio_data_sick)
text(tree.bio_data_sick, pretty=0) 

tree.bio_data_sick

tree.pred = predict(tree.bio_data_sick, bio_data_sick[-train,], type="class")
with(bio_data_sick[-train,], table(tree.pred, Acute))
(119+118)/265 # 89.43% accuracy, seed(102)

cv.bio_data_sick = cv.tree(tree.bio_data_sick, FUN = prune.misclass)
cv.bio_data_sick
plot(cv.bio_data_sick) 
# very large drop and then subsequent decline in the misclasification rate as trees are pruned smaller and smaller
# I will pick somewhere in the very low misclassification rate; size of 8

prune.bio_data_sick = prune.misclass(tree.bio_data_sick, best = 8)
plot(prune.bio_data_sick)
text(prune.bio_data_sick, pretty=0) 

tree.pred = predict(prune.bio_data_sick, bio_data_sick[-train,], type="class") 
with(bio_data_sick[-train,], table(tree.pred, Acute))
(119+116)/265 # 88.67925% accuracy, best=8, seed(102), best performing model
(111+121)/265 # 87.54717% accuracy, best=6, seed(102)
(122+110)/265 # 87.54717% accuracy, best=10, seed(102)
