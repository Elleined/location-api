# region-province-city-baranggay-api
Creating Location API for user address and delivery address registration

# Configuration
 - Create a db named location_db
   ```
     CREATE DATABASE location_db;
   ```
 - Change application.properties
   ```
     spring.jpa.hibernate.ddl-auto=create
   ```
 - When you first run this all regions, provinces, cities, and baranggay will be saved automatically.
 You dont need to do anything it will create the table and insert the values. It will take sometime
 to save all the records and wait for the log message that says all records saved.
 - After your first run change otherwise it will recreate the table amd recorda again.
   ```
     spring.jpa.hibernate.ddl-auto=update
   ```
   
# Features
 - Save and save all region, province, city, baranggay
 - Get region, province, city, baranggay by id
 - Get all region
 - Get all province based on region
 - Get all city based on province
 - Get all baranggay based on city

# Technologies used
 - Spring boot
 - Spring mvc
 - Spring data jpa
 - Softwares used
   - MySQL
   - Postman
   - IntelliJ
 
# Access all endpoints in Postman easily
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/26932885-6be305a4-8400-4128-aff3-6da8e9440320?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D26932885-6be305a4-8400-4128-aff3-6da8e9440320%26entityType%3Dcollection%26workspaceId%3D0e6edea3-3d68-40e8-ae27-886affdb537b)

