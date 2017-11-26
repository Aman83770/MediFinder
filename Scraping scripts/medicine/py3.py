import requests
import re
from bs4 import BeautifulSoup
url = "http://www.drugs.com/imprints.html"
r = requests.get(url)
soup = BeautifulSoup(r.content,"html.parser")

###pick up links from url and append in 'a'

links = soup.find_all(href=re.compile("imprints"))
a = []
for link in links:
    link1 = "http://www.drugs.com" + link.get("href")
    a.append(link1)
b= []
#new = a[0]
#print new

###for each link in 'a' pick up links contained in it and append in 'b'

for each_link in a:
    url1 = each_link
    new_r = requests.get(url1)
    new_soup = BeautifulSoup(new_r.content,"html.parser")
    ##new_links = new_soup.find_all(href=re.compile("imprints"))
    new_links = new_soup.find_all(href=re.compile("/imprints/"))
    for link in new_links:
        link2 = "http://www.drugs.com" + link.get("href")
        ##link2 = each_link + link.get("href")
        ##each_link_1 = each_link[:-5]
        ##link2 = each_link_1 + link.get("href")
        b.append(link2)
print len(b)
##for i in b:
##    print i



