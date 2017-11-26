import requests
from bs4 import BeautifulSoup

url = "https://www.practo.com/delhi/doctors?"
r = requests.get(url)
soup = BeautifulSoup(r.content,"html.parser")

div_listing = soup.find_all("div","listing-row")

def fx(value):
    if value==None:
        return "NULL"
    else:
        var = value.text
        var = var.replace('\n',' ')
        var = var.strip()
        return var



for each in div_listing:
    doc_details = each.find("div","doc-details-block")
    temp = doc_details.find("h2",{"itemprop":"name"})
    name = fx(temp)
    temp = doc_details.find("p","doc-qualifications")
    qualifications = fx(temp)
    temp = doc_details.find("p","doc-exp-years")
    experience = fx(temp)
    temp = doc_details.find("span",{"itemprop":"medicalSpecialty"})
    speciality = fx(temp)
    temp = doc_details.find("span",{"itemprop":"name"})
    clinic = fx(temp)
    
    doc_availability = each.find("div","doc-availability-block")
    temp = doc_availability.find("span",{"itemprop":"addressLocality"})
    locality = fx(temp)
    temp = doc_availability.find("span",{"itemprop":"addressRegion"})
    city = fx(temp)
    temp = doc_availability.find("span","fees-amount")
    fees = fx(temp)
    temp = doc_availability.find("span","strong")
    days = fx(temp)
    temp = doc_availability.find("span","hours-timing")
    timing = fx(temp)
    print name," ",qualifications," ",experience," ",speciality," ",clinic," ",locality," ",city," ",fees," ",days," ",timing


##i=1
##for each in div_listing:
##    print i
##    i+=1
print "hello "
