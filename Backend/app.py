from flask import Flask, render_template, request, jsonify, json
from flask.ext.mysql import MySQL

app = Flask(__name__)

mysql = MySQL()

##MySQL configurations
app.config['MYSQL_DATABASE_USER'] = 'root'
app.config['MYSQL_DATABASE_PASSWORD'] = 'meena.'
app.config['MYSQL_DATABASE_DB'] = 'root1'
app.config['MYSQL_DATABASE_HOST'] = '127.0.0.1'
mysql.init_app(app)

@app.route('/')
def main():
    return render_template('Index.html')

@app.errorhandler(404)
def page_not_found(e):
    return render_template('Error404.html'), 404

@app.route('/Search')
def SearchPage():
    return render_template('Search.html')

@app.route('/Services')
def Servies():
    return render_template('Services.html')

@app.route('/Feedback')
def Feedback():
    return render_template('Feedback.html')

@app.route('/FindStoreHere')
def FindStoreHere():
    return render_template('Search.html')

@app.route('/PhysicianDirectory')
def PhysicianDirectory():
    return render_template('Search.html')

@app.route('/#section2')
def section2():
    return render_template('Index.html')

@app.route('/#section3')
def section3():
    return render_template('Index.html')

@app.route('/#section4')
def section4():
    return render_template('Index.html')

@app.route('/#section5')
def section5():
    return render_template('Index.html')

@app.route('/#section6')
def section6():
    return render_template('Index.html')

@app.route('/#section7')
def section7():
    return render_template('Index.html')

@app.route('/#section8')
def section8():
    return render_template('Index.html')

@app.route('/ChemistProfile')
def ChemistProfile():
    return render_template('ChemistProfile.html')

@app.route('/DoctorProfile')
def DoctorProfile():
    return render_template('DoctorProfile.html')

@app.route('/Doctor')
def Doctor():
    return render_template('Doctor.html')

@app.route('/Chemist')
def Chemist():
    return render_template('Chemist.html')

@app.route('/Hospital')
def Hospital():
    return render_template('Hospital.html')

@app.route('/HospitalProfile')
def HospitalProfile():
    return render_template('HospitalProfile.html')

@app.route('/DiagnosticCentre')
def DiagnosticCentre():
    return render_template('DiagnosticCentre.html')

@app.route('/Support')
def Support():
    return render_template('Support.html')

@app.route('/AboutUs')
def AboutUs():
    return render_template('AboutUs.html')

@app.route('/ChangePassword')
def ChangePassword():
    return render_template('ChangePassword.html')

@app.route('/PillsIdentifier')
def PillsIdentifier():
    return render_template('PillsIdentifier.html')

@app.route('/SignUp/',methods=['POST'])
def SignUp():
    #request.form = json.loads(request.data)
    hospitalname = request.form['HospitalName']
    speciality = request.form['Speciality']
    fname = request.form['FirstName']
    storename = request.form['StoreName']
    lname = request.form['LastName']
    diagnosticname = request.form['DiagnosticName']
    gender = request.form['Gender']
    password = request.form['Password']
    email = request.form['Email']
    workarea = request.form['WorkArea']

    if fname and lname and gender and email and workarea and password:

        if workarea=='Doctor':
            cursor = mysql.get_db().cursor()
            cursor.execute("select exists (select 1 from doc3 where email_id in (%s))",[email])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            existing_user = entries[0][0]
            if existing_user:
                message = "EmailID already exists!"
                return jsonify(message=message)
            else:
                cursor = mysql.get_db().cursor()
                fullname = fname + ' ' +lname
                cursor.execute("insert into doc1(name) values(%s)",[fullname])
                mysql.get_db().commit()
                cursor.execute("insert into doc2(specialization) values(%s)",[speciality])
                mysql.get_db().commit()
                cursor.execute("insert into doc3(email_id,password) values(%s,%s)",([email],[password]))
                mysql.get_db().commit()
                cursor.execute("Select doc_id from doc3 where email_id in (%s)",[email])
                mysql.get_db().commit()
                entry = cursor.fetchall()
                pri = entry[0][0]
                cursor.execute("insert into email(primary_key,email,table_name) values (%s,%s,'doc3')",([pri],[email]))
                mysql.get_db().commit()
                message = "Successfully registered!"
                return jsonify(message=message)

        elif workarea=='Diagnostic Centre':
            cursor = mysql.get_db().cursor()
            cursor.execute("select exists (select 1 from diag1 where emailid in (%s))",[email])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            existing_user = entries[0][0]
            if existing_user:
                message = "Name already exists!"
                return jsonify(message=message)
            else:
                cursor = mysql.get_db().cursor()
                cursor.execute("insert into diag1(name,emailid) values(%s,%s)",([diagnosticname],[email]))
                mysql.get_db().commit()
                cursor.execute("insert into diag2(password) values(%s)",[password])
                mysql.get_db().commit()
                cursor.execute("Select d_id from diag1 where emailid in (%s)",[email])
                mysql.get_db().commit()
                entry = cursor.fetchall()
                pri = entry[0][0]
                cursor.execute("insert into email(primary_key,email,table_name) values (%s,%s,'diag1')",([pri],[email]))
                mysql.get_db().commit()
                message = "Successfully registered!"
                return jsonify(message=message)

        elif workarea=='Hospital':
            cursor = mysql.get_db().cursor()
            cursor.execute("select exists (select 1 from hospital1 where emailid  in (%s))",[email])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            existing_user = entries[0][0]
            if existing_user:
                message = "Name already exists!"
                return jsonify(message=message)
            else:
                cursor = mysql.get_db().cursor()
                cursor.execute("insert into hospital (name) values(%s)",[hospitalname])
                mysql.get_db().commit()
                cursor.execute("insert into hospital1 (emailid,password) values(%s,%s)",([email],[password]))
                mysql.get_db().commit()
                cursor.execute("Select h_id from hospital1 where emailid in (%s)",[email])
                mysql.get_db().commit()
                entry = cursor.fetchall()
                pri = entry[0][0]
                cursor.execute("insert into email(primary_key,email,table_name) values (%s,%s,'hospital1')",([pri],[email]))
                mysql.get_db().commit()
                message = "Successfully registered!"
                return jsonify(message=message)

        elif workarea=='Chemist':
            cursor = mysql.get_db().cursor()
            cursor.execute("select exists (select 1 from medi4 where emailid in (%s))",[email])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            existing_user = entries[0][0]
            if existing_user:
                message = "Name already exists!"
                return jsonify(message=message)
            else:
                cursor = mysql.get_db().cursor()
                cursor.execute("insert into medi1 (name) values(%s)",[storename])
                mysql.get_db().commit()
                cursor.execute("insert into medi4 (emailid,password) values(%s,%s)",([email],[password]))
                mysql.get_db().commit()
                cursor.execute("Select store_id from medi4 where emailid in (%s)",[email])
                mysql.get_db().commit()
                entry = cursor.fetchall()
                pri = entry[0][0]
                cursor.execute("insert into email(primary_key,email,table_name) values (%s,%s,'medi4')",([pri],[email]))
                mysql.get_db().commit()
                message = "Successfully registered!"
                return jsonify(message=message)

    else:
        message = "Enter the required fields"
        return jsonify(message=message)

@app.route('/Login/',methods=['POST','GET'])
def Login():
    # request.form = json.loads(request.data)
    email = request.form['UserName']
    password = request.form['Password']

    if email and password:
        cursor = mysql.get_db().cursor()
        cursor.execute("Select exists (select 1 from email where email in (%s))",[email])
        mysql.get_db().commit()
        entries = cursor.fetchall()
        answer = entries[0][0]
        if answer:
            cursor = mysql.get_db().cursor()
            cursor.execute("select primary_key,email,table_name from email where email in (%s)",[email])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            pk = entries[0][0]
            email1 = entries[0][1]
            table = entries[0][2]
            
            if table=='diag1':  
                cursor = mysql.get_db().cursor()
                cursor.execute("Select password from diag2 where d_id in (%s)",[pk])
                mysql.get_db().commit()
                entries = cursor.fetchall()
                pwd = entries[0][0]
                if password==pwd:
                    cursor = mysql.get_db().cursor()
                    cursor.execute("select name,street,locality,region,postalcode,timings,website from diag1 where d_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries = cursor.fetchall()
                    name = entries[0][0]
                    street = entries[0][1]
                    locality = entries[0][2]
                    region = entries[0][3]
                    postalcode = entries[0][4]
                    timings = entries[0][5]
                    website = entries[0][6]
                    address = street + ' ' + locality + ' ' + region + ' ' + postalcode
                    return jsonify(name=name,address=address,timings=timings,website=website,WorkArea="diag1",Id=pk)

                else:
                    message = "Invalid UserName or Password"
                    return jsonify(message=message)

            elif table=='doc3':
                cursor = mysql.get_db().cursor()
                cursor.execute("Select password from doc3 where doc_id in (%s)",[pk])
                mysql.get_db().commit()
                entries = cursor.fetchall()
                pwd = entries[0][0]
                if password==pwd:
                    cursor = mysql.get_db().cursor()
                    cursor.execute("select name from doc1 where doc_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries = cursor.fetchall()
                    name = entries[0][0]
                    cursor.execute("select qualification,specialization,locality,city,fees,timings from doc2 where doc_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries1 = cursor.fetchall()
                    qualification = entries1[0][0]
                    specialization = entries1[0][1]
                    locality = entries1[0][2]
                    city = entries1[0][3]
                    fees = entries1[0][4]
                    timings = entries1[0][5]
                    quali = qualification + ' ' + specialization
                    address = locality + ' ' + city
                    cursor.execute("select email_id from doc3 where doc_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries2 = cursor.fetchall()
                    emailid = entries2[0][0]
                    return jsonify(name=name,qualification=quali,address=address,fees=fees,timings=timings,emailid=emailid,WorkArea="doc3",Id=pk)

                else:
                    message = "Invalid UserName or Password"
                    return jsonify(message=message)

            elif table=='hospital1':
                cursor = mysql.get_db().cursor()
                cursor.execute("Select password from hospital1 where h_id in (%s)",[pk])
                mysql.get_db().commit()
                entries = cursor.fetchall()
                pwd = entries[0][0]
                if password==pwd:
                    cursor = mysql.get_db().cursor()
                    cursor.execute("select name,city,address,contact from hospital where h_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries = cursor.fetchall()
                    name = entries[0][0]
                    city = entries[0][1]
                    add = entries[0][2]
                    contact = entries[0][3]
                    address = add + ' ' + city
                    cursor.execute("select emailid from hospital1 where h_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries1 = cursor.fetchall()
                    emailid = entries1[0][0]
                    return jsonify(name=name,contact=contact,address=address,emailid=emailid,Id=pk,WorkArea="hospital1")

                else:
                    message = "Invalid UserName or Password"
                    return jsonify(message=message)

            elif table=='medi4':
                cursor = mysql.get_db().cursor()
                cursor.execute("Select password from medi4 where store_id in (%s)",[pk])
                mysql.get_db().commit()
                entries = cursor.fetchall()
                pwd = entries[0][0]
                if password==pwd:
                    cursor = mysql.get_db().cursor()
                    cursor.execute("select name,telno,street,locality,region,postalcode from medi1 where store_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries = cursor.fetchall()
                    name = entries[0][0]
                    telno = entries[0][1]
                    street = entries[0][2]
                    locality = entries[0][3]
                    region = entries[0][4]
                    postalcode = entries[0][5]
                    address = street + ' ' + locality + ' ' + region + ' ' + postalcode
                    cursor.execute("select emailid from medi4 where store_id in (%s)",[pk])
                    mysql.get_db().commit()
                    entries1 = cursor.fetchall()
                    emailid = entries1[0][0]
                    return jsonify(name=name,telno=telno,address=address,emailid=emailid,Id=pk,WorkArea="medi4")

                else:
                    message = "Invalid UserName or Password"
                    return jsonify(message=message)

        else:
            message = "Invalid UserName or Password"
            return jsonify(message=message)

    else:
        message = "Enter the rquired fields!"
        return jsonify(message=message)

@app.route('/PillsIdentifier/',methods=['POST','GET'])
def PillsIdentifierPage():
    # request.form    = json.loads(request.data)
    imprint         = request.form['Imprint']
    color           = request.form['Color']
    shape           = request.form['Shape']
    imprint         = ' ' + imprint + ' '
    print imprint, color, shape

    if imprint and color and shape:
        cursor = mysql.get_db().cursor()
        cursor.execute("select name,salts,strength,color,shape,imprint from medi where color in (%s) and shape in (%s) and imprint in (%s) limit 1",([color],[shape],[imprint]))
        mysql.get_db().commit()
        entries = cursor.fetchall()
        if not entries:
            return jsonify(message="Not Available")
        print entries
        # print entries[0]
        name = entries[0][0] 
        salts = entries[0][1] 
        strength = entries[0][2]
        return jsonify(name=name,salts=salts,strength=strength,color=color,shape=shape,imprint=imprint)

    else:
        message = "Enter the required fields"
        return jsonify(message=message)

@app.route('/CheckPillsAvailability/',methods=['POST','GET'])
def CheckPillsAvailability():
    # request.form = json.loads(request.data)
    _id = request.form['Id']
    name = request.form['Name']
    # print name,' ',_id
    _id = _id[:-1]
    _id = _id[1:]
    # name = name[:-1]
    # name = name[1:]
    print _id,'', name
    if _id and name:
        cursor = mysql.get_db().cursor()
        cursor.execute("select med_id from medi where name in (%s) limit 1",[name])
        mysql.get_db().commit()
        entry = cursor.fetchall()
        if not entry:
            return jsonify(message='Not Available')

        medid = entry[0][0]
        # medid = 1
        cursor = mysql.get_db().cursor()
        cursor.execute("select exists (select 1 from medi2 where med_id = %s and store_id = %s)",([medid],[_id]))
        mysql.get_db().commit()
        entries = cursor.fetchall()
        value = entries[0][0]
        if value:
        # if 1:
            message = "Available"
            return jsonify(message=message)
        else:
            message = "Not available"
            return jsonify(message=message)
        
    else:
        message = "Enter required fields!"
        return jsonify(message=message)

@app.route('/Search/',methods=['POST','GET'])
def Search():
    # request.form = json.loads(request.data)
    _id = request.form['ID']
    _name = request.form['Name']
    _table = request.form['Table']

    if _id and _name and _table:

        if _table=='diag1':
            cursor = mysql.get_db().cursor()
            cursor.execute("select name,street,locality,region,postalcode,timings,website from diag1 where d_id in (%s)",[_id])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            name = entries[0][0]
            street = entries[0][1]
            locality = entries[0][2]
            region = entries[0][3]
            postalcode = entries[0][4]
            timings = entries[0][5]
            website = entries[0][6]
            address = street + ' ' + locality + ' ' + region + ' ' + postalcode
            return jsonify(name=name,address=address,timings=timings,website=website)

        elif _table=='doc1':
            cursor = mysql.get_db().cursor()
            cursor.execute("select name from doc1 where doc_id in (%s)",[_id])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            name = entries[0][0]
            cursor.execute("select qualification,specialization,locality,city,fees,timings from doc2 where doc_id in (%s)",[_id])
            mysql.get_db().commit()
            entries1 = cursor.fetchall()
            qualification = entries1[0][0]
            specialization = entries1[0][1]
            locality = entries1[0][2]
            city = entries1[0][3]
            fees = entries1[0][4]
            timings = entries1[0][5]
            quali = qualification + ' ' + specialization
            address = locality + ' ' + city
            cursor.execute("select email_id from doc3 where doc_id in (%s)",[_id])
            mysql.get_db().commit()
            entries2 = cursor.fetchall()
            emailid = entries2[0][0]
            return jsonify(name=name,qualification=quali,address=address,fees=fees,timings=timings,emailid=emailid)

        elif _table=='hospital':
            cursor = mysql.get_db().cursor()
            cursor.execute("select name,city,address,contact from hospital where h_id in (%s)",[_id])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            name = entries[0][0]
            city = entries[0][1]
            add = entries[0][2]
            contact = entries[0][3]
            address = add + ' ' + city
            cursor.execute("select emailid from hospital1 where h_id in (%s)",[_id])
            mysql.get_db().commit()
            entries1 = cursor.fetchall()
            emailid = entries1[0][0]
            return jsonify(name=name,contact=contact,address=address,emailid=emailid)


        elif _table=='medi1':
            cursor = mysql.get_db().cursor()
            cursor.execute("select name,telno,street,locality,region,postalcode from medi1 where store_id in (%s)",[_id])
            mysql.get_db().commit()
            entries = cursor.fetchall()
            name = entries[0][0]
            telno = entries[0][1]
            street = entries[0][2]
            locality = entries[0][3]
            region = entries[0][4]
            postalcode = entries[0][5]
            address = street + ' ' + locality + ' ' + region + ' ' + postalcode
            cursor.execute("select emailid from medi4 where store_id in (%s)",[_id])
            mysql.get_db().commit()
            entries1 = cursor.fetchall()
            emailid = entries1[0][0]
            return jsonify(name=name,telno=telno,address=address,emailid=emailid)

        else:
            message = "Something went wrong!"
            return jsonify(message=message)

    else:
        message = "Enter keyword for search!"
        return jsonify(message=message)

@app.route('/Feedback1/',methods=['POST','GET'])
def Feedback1():
    # request.form = json.loads(request.data)
    name = request.form['Name']
    email = request.form['Emailid']
    msg = request.fomr['Message']
    rating = request.form['Rating']
    body_text = msg + 'Rating: ' + rating

    def send_mail(from_email=None, to_email=None, subject=None, body=None):
        """
        Generic Function for sending emails
        """

        mandrill_cli = mandrill.Mandrill(API_KEY)

        message = {
            'from_email': email,
            'html': body,
            'subject': subject,
            'to': to_email,
        }

        b = mandrill_cli.messages.send(message=message, async=False)
        print 'Mail Sent'
        return b

    to = {
        "email": "medifindergroup@gmail.com",
        "name": "MediFinder",
        "type": "to"
    }

    print send_mail(to_email=[to],subject=name, body=body_text)
    return jsonify(message='Mail Sent')

@app.route('/Support1/',methods=['POST','GET'])
def Support1():
    # request.form = json.loads(request.data)
    name = request.form['Name']
    email = request.form['Emailid']
    msg = request.fomr['Message']
    _subject = request.form['Subject']

    def send_mail(from_email=None, to_email=None, subject=None, body=None):
        """
        Generic Function for sending emails
        """

        mandrill_cli = mandrill.Mandrill(API_KEY)

        message = {
            'from_email': email,
            'html': body,
            'subject': subject,
            'to': to_email,
        }

        b = mandrill_cli.messages.send(message=message, async=False)
        print 'Mail Sent'
        return b

    to = {
        "email": "medifindergroup@gmail.com",
        "name": "MediFinder",
        "type": "to"
    }

    print send_mail(to_email=[to],subject=_subject, body=msg)
    return jsonify(message='Mail Sent')


if __name__=='__main__':
    app.run(debug=True)
