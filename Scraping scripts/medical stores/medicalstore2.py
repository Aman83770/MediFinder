import requests
from bs4 import BeautifulSoup

temp_url = "http://yellowpages.sulekha.com/pharmacies_delhi_"
j=1
a=[]
while j<192:
    url = temp_url + str(j)
    a.append(url)
    j+=1

b=[]
for each_link in a:
    r = requests.get(each_link)
    soup = BeautifulSoup(r.content,"html.parser")
    links = soup.find_all("a","YPTRACK GAQ_C_BUSL")
    for link in links:
        u1 = link.get("href")
        url1 = "http://yellowpages.sulekha.com" + u1
        b.append(url1)

print len(b)
