##certain array d
##certain array c
##if d[0] is generic name
##then assign var0 with c[0]
##elif d[1] is generic name
##then assign var0 with c[1]
##


import MySQLdb
import requests
from bs4 import BeautifulSoup
url="http://www.drugs.com/imprints/cer-rev-18521.html"
r = requests.get(url)
soup = BeautifulSoup(r.content,"html.parser")

div = soup.find("div","pid-info-grid")
dd = div.find_all("dd")
dt = div.find_all("dt")
c=[]
d=[]
for i in dd:
    c.append(i.text)
for j in dt:
    d.append(j.text)
    
###extract heading

div = soup.find("div","pid-info")
ahref = div.find_all("a","big bold")
var = ahref[0].text

###remove spaces and line feed from imprint data


def fx(d,x):
    i=0
    while i<len(d):
        if d[i]=="Generic Name:" and x=="Generic Name:":
            return i
        elif d[i]=="Imprint:" and x=="Imprint:":
            return i
        elif d[i]=="Strength:" and x=="Strength:":
            return i
        elif d[i]=="Color:" and x=="Color:":
            return i
        elif d[i]=="Shape:" and x=="Shape:":
            return i
        i+=1
    return 40



index = fx(d,"Generic Name:")
if index!=40:
    var0 = c[index]
else:
    var0 = "NULL"

index = fx(d,"Imprint:")
if index!=40:
    var5 = c[index]
else:
    var5 = "NULL"

index = fx(d,"Strength:")
if index!=40:
    var2 = c[index]
else:
    var2 = "NULL"

index = fx(d,"Color:")
if index!=40:
    var3 = c[index]
else:
    var3 = "NULL"

index = fx(d,"Shape:")
if index!=40:
    var4 = c[index]
else:
    var4 = "NULL"

print var5

var57 = var5.replace(" ","")
var6 = var57.replace("\n"," ")

print var," ",var0," ",var2," ",var3," ",var4," ",var6," "

var7 = var6.replace(u'\u2191','-').encode('latin-1')

print var7

conn = MySQLdb.connect("127.0.0.1","Shashank","root","medi1")
cc = conn.cursor()
cc.execute("insert into medi (name) values (%s) ",[var4.encode('latin-1','replace')])
conn.commit()

##value = var6.encode("utf-8")
##print value
