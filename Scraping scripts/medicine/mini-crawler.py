import MySQLdb
import requests
from bs4 import BeautifulSoup
url="http://www.drugs.com/imprints/a-25mg-or-2861.html"
r = requests.get(url)
soup = BeautifulSoup(r.content,"html.parser")

#####extract data other than heading
##there exists a problem here.....some medicines don't have either salts or shape
##or any other value.....this destroys the table structure....because imprint may
##get eneterd in the salts field in case of a missing salts record!!
##therefore adding certain data

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

###take everything in variables 

var0 = c[0]
var2 = c[2]
var3 = c[3]
var4 = c[4]

###remove spaces and line feed from imprint data

var5 = c[1].replace(" ","")
var6 = var5.replace("\n"," ")

###connect to mysql and perform insert

conn = MySQLdb.connect("127.0.0.1","Shashank","root","root1")
cc = conn.cursor()
cc.execute("insert into medicine (name,salts,strength,color,shape,imprint) values (%s,%s,%s,%s,%s,%s) ",(var,var0,var2,var3,var4,var6))
conn.commit()
