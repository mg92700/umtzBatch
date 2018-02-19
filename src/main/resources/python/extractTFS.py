import pycurl
import json
import csv
from io import BytesIO

id=[]
repo=[]
name='teamcity_build'
pwd='T3@c1tyBu'

buffer = BytesIO()
i=0

## ---- Get repository list ----#
url="http://echosens-tfs.echosens.local:8080/tfs/EchoCollection/_apis/git/85e8c907-de6f-48e3-9c83-af7feebf837f/repositories"
curl = pycurl.Curl()
curl.setopt(pycurl.URL, url)
curl.setopt(pycurl.SSL_VERIFYPEER, 0)
curl.setopt(pycurl.HTTPAUTH, pycurl.HTTPAUTH_NTLM)
curl.setopt(pycurl.USERPWD, "{}:{}".format(name, pwd))
curl.setopt(pycurl.WRITEDATA, buffer)
curl.perform()
curl.close()
body = buffer.getvalue().decode('iso-8859-1')

#json conversion
json_response=json.loads(body)
count=json_response['count']

#Extract ID
for value in range(0,count):
	id.append(json_response['value'][value]['id'])
	repo.append(json_response['value'][value]['name'])
#Extract PR for each ID
with open('fileName.csv', 'w') as f:
	for PR in id:
		url= "http://echosens-tfs.echosens.local:8080/tfs/EchoCollection/_apis/git/repositories/%s/pullRequests?status=ACTIVE"%(PR)
		buffer = BytesIO()
		curl = pycurl.Curl()
		curl.setopt(pycurl.URL, url)
		curl.setopt(pycurl.SSL_VERIFYPEER, 0)
		curl.setopt(pycurl.HTTPAUTH, pycurl.HTTPAUTH_NTLM)
		curl.setopt(pycurl.USERPWD, "{}:{}".format(name, pwd))
		curl.setopt(pycurl.WRITEFUNCTION, buffer.write)
		curl.perform()
		curl.close()
		body = buffer.getvalue().decode('iso-8859-1')
		#json conversion
		json_response=json.loads(body)
		PR_count=json_response['count']
		for value in range(0,PR_count):
			PR_number=json_response['value'][value]['pullRequestId']
			PR_requester=json_response['value'][value]['createdBy']['displayName']
			PR_date=json_response['value'][value]['creationDate']
			PR_title=json_response['value'][value]['title']
			print(json_response)
			line="%s,%s,%s,%s,%s"%(repo[i],PR,PR_number,PR_date,PR_title)
			for s in line:
				f.write(s)
			f.write('\n')
		i=i+1

