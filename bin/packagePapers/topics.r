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
arr<-as.character(list.files("C:\\Users\\Omobola\\Desktop\\DOCUMENTS",full.names=FALSE,include.dirs=FALSE))

##Print the content of the directory
 
##for(i in 1:length(arr))
 
 for(i in 1:4)
 {
	newLink<-paste("C:\\Users\\Omobola\\Desktop\\DOCUMENTS\\",arr[i],sep="")
	print(newLink)
	
	outputFreq<-paste("C:\\Users\\Omobola\\Desktop\\TermFreq\\",arr[i],sep="")
	outputTopics<-paste("C:\\Users\\Omobola\\Desktop\\Topics\\",arr[i],sep="")
	outputVocabs<-paste("C:\\Users\\Omobola\\Desktop\\Vocabs\\",arr[i],sep="")
	
	txtInput <- readLines(newLink)
	##print(txtInput)
	
	docs<-c(txtInput)
	
	##print(docs)
	
	
	corpus<-Corpus(VectorSource(docs))
	corpus <-tm_map(corpus,removePunctuation)
	corp <- tm_map(corp, stripWhitespace)
	corp <- tm_map(corp, tolower)
	corp <- tm_map(corp, removeWords,stopwords('english'))
	
	##lex<-lexicalize(corp)
	
	dtm <- DocumentTermMatrix(corpus,control = list(stemming = TRUE, stopwords = TRUE, minWordLength = 3, removeNumbers = TRUE))
	dtm <- removeSparseTerms(dtm, 0.99)
	
	##frequentTerm<-findFreqTerms(dtm,5)
	
	
	lda <- LDA(dtm, control = list(alpha = 0.1), k = 10)
	topic<-get_terms(lda,15)

	print(topic)
	##write(frequentTerm,outputFreq)
	##write(topic,outputTopics)
	
	}
	



	

