package dev.anshul.EcomProductService.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
/*
* jpa repository do not have basic attribute implementation--no
* jpa repo does nothing for you, actually it comes from hibernate iteself
* hibernate is the lower level of jpa. jpa library is higher level.hibernate is top of jdbc. you can use feature of hiberate using jpa
* jpa repo does not tell you what basic attribute you should add or not add
* it helps you to work but will not tell you that  automatically giving the attribute. it provide
    YOU THE FACILITY but you have to use it
    jpa is the wrapper but internally hibernate does the job..jpa is specification and hibernate is the implemenation* * */

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
//in postman output when we post api then base model was not coming in the output coz getter and setter was not there
// and the attributes were private so will not be able to access without getter and setter
@Getter
@Setter
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;// its a binary object. storing time will be more but practically it wont make the diff
    @CreationTimestamp
    private Instant createdAt; //number of seconds/ nanoseconds since 1st Jan, 1970 UTC and its latest one from the timestamp. it saves timestamp in long epoch
    // instant is an object. you cannot play with it. at database level we should not
    @UpdateTimestamp
    private Instant updatedAt;
}
