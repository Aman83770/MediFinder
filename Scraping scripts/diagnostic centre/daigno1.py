from bs4 import BeautifulSoup
import requests
import re
import MySQLdb

def fx(temp):
    if temp==None:
        return "NULL"
    else:
        temp = temp.text
        temp = temp.strip()
        temp = temp.replace("\n"," ")
        return temp



url = "http://yellowpages.sulekha.com/delhi/n-2-imaging-consultants-pvt-ltd-new-delhi-h-o-delhi_contact-address"
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
temp = ul.find("span",{"itemprop":"email"})
email = fx(temp)
temp = ul.find("a","weblink YPTRACK GAQ_C_TOPWEBSITE")
website = fx(temp)
##temp = ul.find("div","profile-details")
##temp = temp.find("div","profile-child")
##person = fx(temp)
temp = ul.find("time",{"itemprop":"openingHours"})
timings = fx(temp)
temp = soup.find("span",{"itemprop":"name"})
name = fx(temp)
print telno, " ",email," ",website," ",timings," ",street, " ",locality," ",region," ",postalcode,"\n",name


##conn = MySQLdb.connect("127.0.0.1","Shashank","root","root1")
##c = conn.cursor()
##c.execute("insert into diag(telno,email,web) values(%s,%s,%s)",(telno,email,website))
##conn.commit()
