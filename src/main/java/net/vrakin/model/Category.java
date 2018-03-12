package net.vrakin.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @Column(name="CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(name = "NAME")
    @Size(min = 3, message = "Enter at least 3 characters")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @ManyToMany
    @JoinTable(name = "CATEGORY_SERVICE",
            joinColumns = @JoinColumn(name = "CATEGORY_REF"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_REF"))
    private List<Service> serviceList;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Category() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
