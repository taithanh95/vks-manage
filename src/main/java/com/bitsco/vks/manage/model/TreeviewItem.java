package com.bitsco.vks.manage.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeviewItem {
    private String text;
    private String value;
    private boolean internalDisabled;
    private boolean internalChecked;
    private boolean internalCollapsed;
    private List<TreeviewItem> internalChildren;
    private List<TreeviewItem> children;

    public TreeviewItem(String text, String value) {
        this.text = text;
        this.value = value;
    }
}
