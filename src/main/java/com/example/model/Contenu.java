package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="contenu")
public class Contenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contenu_id")
    private Integer contenuId;

    @Column(length = 100, name = "titre")
    private String titre;

    @Column(length = 100, name = "commentaire")
    private String commentaire;

    @Column(length = 10, name = "type")
    private String type;

    public Integer getContenuId() {
        return contenuId;
    }

    public void setContenuId(Integer contenuId) {
        this.contenuId = contenuId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}