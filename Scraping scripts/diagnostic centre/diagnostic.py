from bs4 import BeautifulSoup
import requests
import re
import MySQLdb

temp_url = "http://yellowpages.sulekha.com/diagnostic-centers_delhi_"
b=[]
j=1
while j<57:
    url = temp_url + str(j)
    b.append(url)
    j+=1

print "hello1"

a=[]
for item in b:
    r = requests.get(item)
    print "hello2"
    soup = BeautifulSoup(r.content,"html.parser")
    print "hello3"
    links = soup.find_all("a",{"class":"YPTRACK GAQ_C_BUSL"})
    ##links = soup.find_all(href=re.compile("/delhi/"))
    print "hello4"
    for url1 in links:
        print "hello5"
        url2 = "http://yellowpages.sulekha.com" + url1.get("href")
        a.append(url2)

print "hello2"

conn = MySQLdb.connect("127.0.0.1","Shashank","root","root1")
c = conn.cursor()

print "hello3"

for each_url in a:
    r = requests.get(each_url)
    soup = BeautifulSoup(r.content,"html.parser")

    def fx(temp):
        if temp==None:
            return "NULL"
        else:
            temp = temp.text
            temp = temp.strip()
            temp = temp.replace("\n"," ")
            return temp


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


    c.execute("insert into diag1 (name,street,locality,region,postalcode,timings,telno,emailid,website) values(%s,%s,%s,%s,%s,%s,%s,%s,%s)",([name.encode('latin-1','replace')],[street.encode('latin-1','replace')],[loaclity.encode('latin-1','replace')],[region.encode('latin-1','replace')],[postalcode.encode('latin-1','replace')],[timings.encode('latin-1','replace')],[telno.encode('latin-1','replace')],[email.encode('latin-1','replace')],[website.encode('latin-1','replace')]))
    conn.commit()

print "hello world"

    
