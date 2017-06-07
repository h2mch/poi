# Point-of-Interest
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Example Application for showing
* JavaEE
* CDI
* REST (Hypermedia based)
* Github
* Openshift


Current Deployed version: http://poi-h2mpoi.7e14.starter-us-west-2.openshiftapps.com/


## Usage
some example calls
```bash
curl -X GET http://poi-h2mpoi.7e14.starter-us-west-2.openshiftapps.com/h2mpoi/resources/poi/1 
```


```bash
curl -X POST \
  http://poi-h2mpoi.7e14.starter-us-west-2.openshiftapps.com/h2mpoi/resources/example \
  -H 'content-type: application/json' \
  -d '{
"key1": "value1",
"key2": "value2",
"key3": "value3"
}'
```
