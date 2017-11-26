import urllib
urls=["http://google.com","http://nytimes.com","http://cnn.com"]
i=0
while i<len(urls):
    htmlfile=urllib.urlopen(urls[i])
    htmltext=htmlfile.read()
    print htmltext
    i+=1
