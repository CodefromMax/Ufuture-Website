package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class Interest_listKey implements Serializable {

    @Column(name = "interestListOrder")
    private Long interestListOrder;

    @Column(name = "studentId")
    private String studentId;

    @Override
    public int hashCode(){
        String concatString = String.valueOf(interestListOrder.hashCode()) + String.valueOf(studentId.hashCode());
        return concatString.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof Interest_listKey))
            return false;
        Interest_listKey other = (Interest_listKey) o;
        return interestListOrder.equals(other.interestListOrder) && studentId.equals(other.studentId);
    }

}
