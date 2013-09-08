## Load Packages ##

##install.packages("tm")
##install.packages("topicmodels")
##install.packages("lda")
##install.packages("RTextTools")
##install.packages("Rcpp")

library("tm")
library("topicmodels")
library("lda")
library("RTextTools")
library("Rcpp")


## Read the content of a directory
arr<-as.character(list.files("C:\\Users\\Omobola\\Desktop\\Users",full.names=FALSE,include.dirs=FALSE))


for(i in 1:length(arr))
{
	newLink<-paste("C:\\Users\\Omobola\\Desktop\\Users\\",arr[i],sep="")
	print(newLink)
	
	
	txtInput <- readLines(newLink)
	
	docs<-c(txtInput)
	
	
	outputTopics<-paste("C:\\Users\\Omobola\\Desktop\\UserTopics\\",arr[i],sep="")
	
	corpus<-Corpus(VectorSource(docs))
	corpus <-tm_map(corpus,removePunctuation)
	corpus <- tm_map(corpus, stripWhitespace)
	corpus <- tm_map(corpus, tolower)
	corpus <- tm_map(corpus, removeWords,stopwords('english'))
	
	
	dtm <- DocumentTermMatrix(corpus,control = list(stemming = TRUE, stopwords = TRUE, minWordLength = 3, removeNumbers = TRUE))
	dtm <- removeSparseTerms(dtm, 0.99)
	
	frequentTerm<-findFreqTerms(dtm,2)
	
	
	rowTotals <- apply(dtm, 1, sum)
    dtm2 <- dtm[rowTotals>0]
    dim(dtm2)
	
	k = 10
	
	lda <- LDA(dtm2, control = list(alpha = 0.1), k)
	
	topic<-get_terms(lda,15)
	
	write(topic,outputTopics);
	}
	
	
	



	

