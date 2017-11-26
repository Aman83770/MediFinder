import requests
from bs4 import BeautifulSoup

url = "https://www.practo.com/delhi/doctors?"
a=[]
a.append(url)
j=2
while j<1784:
    append_url = url + "page=" + str(j)
    a.append(append_url)
    j+=1

for i in a:
    print i
