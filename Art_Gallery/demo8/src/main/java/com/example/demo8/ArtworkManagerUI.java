package com.example.demo8;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArtworkManagerUI extends VBox {
    private List<Artwork> artworks;

    public ArtworkManagerUI() {
        artworks = new ArrayList<>();
        artworks.add(new Artwork("Artwork 1", "A beautiful painting", "Wassily Kandinsky", "$1000", "Artwork_1.jpg", 15));
        artworks.add(new Artwork("Artwork 2", "A stunning sculpture", "Pablo Picasso", "$2000", "Artwork_2.jpg", 25));
        artworks.add(new Artwork("Artwork 3", "An abstract piece", "Wassily Kandinsky", "$1500", "Artwork_3.png", 22));
        artworks.add(new Artwork("Artwork 4", "A realistic Painting", "Gustave Courbet", "$3000", "Artwork_4.jpg", 13));

        BorderPane root = new BorderPane();

        Image backgroundImage = new Image(getClass().getResource("/com/example/demo8/Pookie.png").toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        Background bg = new Background(bgImage);
        root.setBackground(bg);

        HBox topNav = new HBox(20);
        topNav.setPadding(new Insets(10));
        topNav.setStyle("-fx-background-color: Pink;");
        topNav.setAlignment(Pos.CENTER_LEFT);

        Button homeButton = new Button("Home");
        Button viewGalleryButton = new Button("View Gallery");
        Button manageArtworksButton = new Button("Manage My Artworks");
        Label greetingLabel = new Label("Welcome to Pookie's Art Gallery");
        greetingLabel.setFont(Font.font("Algerian", 20));
        topNav.getChildren().addAll(homeButton, viewGalleryButton, manageArtworksButton, greetingLabel);
        root.setTop(topNav);

        VBox header = new VBox(10);
        header.setPadding(new Insets(10));
        Label manageLabel = new Label("Manage Your Artworks");
        Button addNewArtworkButton = new Button("Add New Artwork +");
        header.getChildren().addAll(manageLabel, addNewArtworkButton);

        VBox artworkList = new VBox(10);
        artworkList.setPadding(new Insets(10));

        for (int i = 0; i < artworks.size(); i++) {
            artworkList.getChildren().add(createArtworkItem(artworks.get(i), i));
        }

        ScrollPane scrollPane = new ScrollPane(artworkList);
        scrollPane.setFitToWidth(true);

        VBox centerContent = new VBox(10);
        centerContent.getChildren().addAll(header, scrollPane);
        root.setCenter(centerContent);

        Label footerLabel = new Label("© All rights reserved");
        footerLabel.setPadding(new Insets(10));
        footerLabel.setAlignment(Pos.CENTER);
        footerLabel.setStyle("-fx-background-color: Pink;");
        root.setBottom(footerLabel);

        Scene scene = new Scene(root, 800, 600);
        Stage artworkStage = new Stage();
        artworkStage.setTitle("Artwork Manager");
        artworkStage.setScene(scene);
        artworkStage.show();
    }

    private HBox createArtworkItem(Artwork artwork, int index) {
        HBox artworkItem = new HBox(10);
        artworkItem.setPadding(new Insets(10));
        artworkItem.setStyle("-fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #f9f9f9;");

        ImageView artworkImage;
        try {
            artworkImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(artwork.getImageUrl())).toExternalForm()));
            artworkImage.setFitWidth(100);
            artworkImage.setFitHeight(100);
        } catch (NullPointerException e) {
            System.err.println("Image not found for URL: " + artwork.getImageUrl());
            artworkImage = new ImageView(new Image(getClass().getResource("/path/to/placeholder.png").toExternalForm()));
            artworkImage.setFitWidth(100);
            artworkImage.setFitHeight(100);
        }

        VBox details = new VBox(5);
        Label titleLabel = new Label(artwork.getTitle());
        Label descriptionLabel = new Label(artwork.getDescription());
        Label artistLabel = new Label("Artist's Name: " + artwork.getArtist());
        Label priceLabel = new Label("Artwork Price: " + artwork.getPrice());
        Label stockLabel = new Label("Stock Available: " + artwork.getStockAvailable());

        details.getChildren().addAll(titleLabel, descriptionLabel, artistLabel, priceLabel, stockLabel);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        editButton.setOnAction(e -> editArtwork(index));

        deleteButton.setOnAction(e -> deleteArtwork(index));

        buttonBox.getChildren().addAll(editButton, deleteButton);

        artworkItem.getChildren().addAll(artworkImage, details, buttonBox);
        return artworkItem;
    }

    private void editArtwork(int index) {
        Artwork artwork = artworks.get(index);
        System.out.println("Editing Artwork: " + artwork.getTitle());
    }

    private void deleteArtwork(int index) {
        artworks.remove(index);
        System.out.println("Deleted Artwork at index: " + index);
    }
}