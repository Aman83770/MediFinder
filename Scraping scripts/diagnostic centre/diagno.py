from bs4 import BeautifulSoup
import requests
import re

temp_url = "http://yellowpages.sulekha.com/diagnostic-centers_delhi_"
b=[]
j=1
while j<57:
    url = temp_url + str(j)
    b.append(url)
    j+=1


a=[]
for item in b:
    r = requests.get(item)
    soup = BeautifulSoup(r.content,"html.parser")
    links = soup.find_all("a",{"class":"YPTRACK GAQ_C_BUSL"})
    ##links = soup.find_all(href=re.compile("/delhi/"))
    for url1 in links:
        a.append("http://yellowpages.sulekha.com" + url1.get("href"))

print len(a)





##for item in b:
##    print item
