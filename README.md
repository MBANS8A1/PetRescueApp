# Pet Rescue (Adoption) Application

## Application Overview

 This application made in Kotlin will allow you to view and read up on different pets that are available for adoption. You can survey the pets based upon their different traits and upon where they are located geographically. Once you have seen a pet that interests you and you would like to adopt it, click the image of it on the home screen; doing this will take you to the details screen for that particular pet. Once you have finished reviewing the information, note down the contact details and click the "Adopt Me" button at the bottom of the details screen, which will take you to the PetFinder Purina :tm: link for that particular pet.

### Important Application Information

  In order to use this application your will need a PetFinder account; click this [link](https://www.petfinder.com/developers/v2/docs/) and under the "Getting Authenticated" heading click on "create an account".
  Once you have created an account you will need to go to your API account settings page and request an API key (they call it a Client ID) and Client Secret (Secret Key). You can copy and paste them into the 
  data/network/BaseObject.kt file in the variables API_KEY and SECRET_KEY; but if you follow this approach do not commit the project to version control as the keys will be unprotected and open to abuse by someone else to consume the limited number of requests you can make with these credentials. A better way is do what I did, which was to keep the credentials within the local.properties files (which is not committed to version control) and use the BuildConfig logic in the build.gradle.kts file to load the credentials in dynamically at runtime using a Properties object. Or you can use some third-party technique such as using Firebase config to hide your keys.

## Screen Navigation (Please ensure you are connected to the Internet first via an ethernet or WIFI connection)

   1. Home Screen (Images of pets that have no photo will have a dog art image where their image should be)

   * Here you will see the list of pets with their images loaded on the screen. You can change the colour theme to dark theme (and vice-versa back to light theme) if you wish by clicking the lightbulb icon.

   <img src="https://github.com/MBANS8A1/PetRescueApp/blob/ec4e91d83cca41af1bddb5e33b8156de4e456a42/app/src/main/res/drawable/petrescue_image1.png" alt="Home screen (light)" width ="250" height="490" >

   <img src="https://github.com/MBANS8A1/PetRescueApp/blob/ec4e91d83cca41af1bddb5e33b8156de4e456a42/app/src/main/res/drawable/petrescue_image2.png" alt="Home screen (light)" width ="250" height="490" >

   <img src="https://github.com/MBANS8A1/PetRescueApp/blob/ec4e91d83cca41af1bddb5e33b8156de4e456a42/app/src/main/res/drawable/petrescue_image3.png" alt="Home screen with pictures" width ="250" height="490" >

   * When you scroll to the bottom of the screen you can click the "Load More Pets" button, which will load in the next list of pets from the PetFinder Web API that you can scroll down to view.

   <img src="https://github.com/MBANS8A1/PetRescueApp/blob/ec4e91d83cca41af1bddb5e33b8156de4e456a42/app/src/main/res/drawable/petrescue_image4.png" alt="Home screen (light)" width ="250" height="490" >

   2. Detail Screen

   * On this page you will see a larger full width image of the pet along with some relevant traits (e.g. age, colour and breed), description story (if there is one) and contact information left by the owner(s) of the pet. At the bottom will be an "Adopt Me" button you can click that will take you to the PetFinder page for the pet.

   <img src="https://github.com/MBANS8A1/PetRescueApp/blob/ec4e91d83cca41af1bddb5e33b8156de4e456a42/app/src/main/res/drawable/petrescue_image5.png" alt="Detail screen 1" width ="250" height="490" >

   <img src="https://github.com/MBANS8A1/PetRescueApp/blob/ec4e91d83cca41af1bddb5e33b8156de4e456a42/app/src/main/res/drawable/petrescue_image6.png" alt="Detail screen 2" width ="250" height="490" >

   3. PetFinder page for selected pet

   * Here you can see more details about the pet, which are recommended to review before you commit to pet adoption. You can see its health status (e.g. if its vaccinations are up-to-date), personality and clickable icons to send an email or call the owner(s). Also, at the top are more images of the pet you can view.

     //Image of PetFinder page for the selected pet.
