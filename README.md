# Read Me First
This is a demo project to provide basic OCR service using tesseract-ocr library of Google

# Getting Started
1. open application.properties in scr/main/resources and change value of file upload path and tesseract path
2. copy tessdata/eng.traineddata to new location
3. create enviroment varible TESSDATA_PREFIX, value is new location of tesseract
4. run mvn clean install
5. deploy war file to tomcat or any appliocation server
6. open http://{{url}}/ocr-service, can use test.jpg to test

## API Spec

##### URL: /processOCR

##### request parameters:

| Name | type | sample value |
| ------ | ------ | ------ |
| file | MultipartFile | xxx.jpg |

##### sample response body:

["/images/testOCR.jpg", "We are Heroes!"]
