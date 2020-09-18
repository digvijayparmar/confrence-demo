package com.digvijay.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions") //sessions is a DB table name
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) //This jackson property will solve the serialization issue by preventing hirbernate to initialize these peoperties
public class Session {
    public Session(){ //For serialization/deserialization to marshal and unmarshal JSON data

    }
    //Variable names are kept same as DB columns - by doing it JPA will auto bind them
    // and we don't need to annotate them ELSE keep a @at column annotation on each of them

    @Id //This annotation will make session_id as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id value will be filled via DB sequence by JPA
    private long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;

    //Many to Many relationship - Choosing Session the main owner of M2M relationship
    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public long getSession_id() {
        return session_id;
    }

    public void setSession_id(long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }


}
