# 🚘 NextRide - Second-Hand Car Booking Backend

This is the backend system for **NextRide**, a platform for booking and evaluating second-hand cars. Built with **Spring Boot**, it includes:

- 🛡️ JWT-based user authentication
- ☁️ AWS S3 integration for file storage
- 📞 Twilio integration for SMS and WhatsApp notifications

---

## 🛠 Tech Stack

- **Backend**: Java 17, Spring Boot
- **Database**: MySQL
- **Storage**: AWS S3
- **Messaging**: Twilio (SMS/WhatsApp)
- **Authentication**: JWT Token

---

## 📦 Features

✅ User signup/login with OTP via Twilio  
✅ Role-based user system (Admin, Agent, Customer)  
✅ Upload car photos to AWS S3  
✅ Car evaluation system (Agents upload reports/photos)  
✅ Location tracking with reverse geocoding  
✅ JWT-protected API routes  

---

## 🔧 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/shahist03/NextRide-Repo.git
cd NextRide-Repo
# JWT Configuration
jwt.key=your-jwt-secret-key
jwt.expiry=8400000
jwt.issuer=your-jwt-issuer-name

# AWS S3 Configuration
accessKey=your-aws-access-key
secretKey=your-aws-secret-key
region=ap-south-1

cloud.aws.region.static=ap-south-1
cloud.aws.region.auto=false
cloud.aws.stack.auto=false
spring.main.allow-bean-definition-overriding=true

logging.level.com.amazonaws.util.EC2MetadataUtils=error
logging.level.com.amazonaws.internal.InstanceMetadataServiceResource=error

# Twilio Configuration
twilio.account.sid=your-twilio-account-sid
twilio.auth.token=your-twilio-auth-token
twilio.phone.number=your-twilio-phone-number
twilio.whatsapp.number=whatsapp:+your-twilio-whatsapp-number
