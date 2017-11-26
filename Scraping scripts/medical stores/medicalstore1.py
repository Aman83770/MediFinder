import requests
import MySQLdb
from bs4 import BeautifulSoup

def fx(value):
    if value==None:
        return "NULL"
    else:
        value = value.text
        value = value.strip()
        value = value.replace("\n","")
        return value

url = "http://yellowpages.sulekha.com/delhi/siddharth-plus-panchkuian-road-delhi_contact-address"
r = requests.get(url)
soup = BeautifulSoup(r.content,"html.parser")
ul = soup.find("ul","ul-horizontal")
temp = ul.find("em",{"itemprop":"telephone"})
telno = fx(temp)
temp = ul.find("span",{"itemprop":"streetAddress"})
street = fx(temp)
temp = ul.find("span",{"itemprop":"addressLocality"})
locality = fx(temp)
temp = ul.find("span",{"itemprop":"addressRegion"})
region = fx(temp)
temp = ul.find("span",{"itemprop":"postalCode"})
postalcode = fx(temp)
temp = soup.find("span",{"itemprop":"name"})
name = fx(temp)
print name," ",telno," ",street," ",locality," ",region," ",postalcode
