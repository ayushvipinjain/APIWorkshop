
package com.thoughtworks.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoardList {

    @SerializedName("closed")
    private Boolean closed;
    @SerializedName("id")
    private String id;
    @SerializedName("idBoard")
    private String idBoard;
    @SerializedName("limits")
    private Limits limits;
    @SerializedName("name")
    private String name;
    @SerializedName("pos")
    private Double pos;
}
