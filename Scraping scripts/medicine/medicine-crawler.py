import requests
import re
from bs4 import BeautifulSoup
import MySQLdb


url = "http://www.drugs.com/imprints.html"
r = requests.get(url)
soup = BeautifulSoup(r.content,"html.parser")

###pick up links from url and append in 'a'

links = soup.find_all(href=re.compile("imprints"))
a = []
for link in links:
    link1 = "http://www.drugs.com" + link.get("href")
    a.append(link1)

###for each link in 'a' pick up links contained in it and append in 'b'

b=[]
for each_link in a:
    url1 = each_link
    new_r = requests.get(url1)
    new_soup = BeautifulSoup(new_r.content,"html.parser")
    new_links = new_soup.find_all(href=re.compile("/imprints/"))
    for link in new_links:
        link2 = "http://www.drugs.com" + link.get("href")
        b.append(link2)

###now for each link in 'b', scrape data and perform an insert in table

item=-1
for each_url in b:
    item+=1
    print b[item]
    rr = requests.get(each_url)
    soup = BeautifulSoup(rr.content,"html.parser")

    ###extract data other than heading

    div = soup.find("div","pid-info-grid")
    dd = div.find_all("dd")
    c=[]
    for i in dd:
        c.append(i.text)

    ###extract heading

    div1 = soup.find("div","pid-info")
    ahref = div1.find_all("a","big bold")
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

print "Hello World"
