package com.example.controllers;

import com.example.services.DatabaseService;
import com.example.services.QueryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import java.util.Arrays;
import java.util.List;


public class MainController {

    @FXML
    private javafx.scene.control.TextField userIdField;

    @FXML
    private javafx.scene.control.TextField taxonIdField;

    @FXML
    private javafx.scene.control.TextField placeField;

    @FXML
    private javafx.scene.control.TextField latitudeField;

    @FXML
    private javafx.scene.control.TextField longitudeField;

    @FXML
    private javafx.scene.control.TextField licenseField;

    @FXML
    private javafx.scene.control.TextField imageUrlField;

    @FXML
    private javafx.scene.control.TextField imageIdField;

    @FXML
    private javafx.scene.control.TextField observationIdField;

    @FXML
    private javafx.scene.control.TextField identificationLevelField;

    private final DatabaseService databaseService = new DatabaseService();
    private final QueryService queryService = new QueryService();

    @FXML
    private TextArea resultArea;

    public void handleInsertImage(ActionEvent event) {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            int taxonId = Integer.parseInt(taxonIdField.getText());
            String place = placeField.getText();
            java.math.BigDecimal latitude = new java.math.BigDecimal(latitudeField.getText());
            java.math.BigDecimal longitude = new java.math.BigDecimal(longitudeField.getText());
            String license = licenseField.getText();

            databaseService.insertarImagen(
                    userId,
                    taxonId,
                    new java.util.Date(),
                    place,
                    latitude,
                    longitude,
                    license
            );
            showAlert("Éxito", "Imagen insertada correctamente.");
        } catch (Exception e) {
            showAlert("Error", "No se pudo insertar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleInsertObservation(ActionEvent event) {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            int taxonId = Integer.parseInt(taxonIdField.getText());
            java.math.BigDecimal latitude = new java.math.BigDecimal(latitudeField.getText());
            java.math.BigDecimal longitude = new java.math.BigDecimal(longitudeField.getText());
            String imageUrl = imageUrlField.getText(); // Asegúrate de tener este TextField
            int imageId = Integer.parseInt(imageIdField.getText()); // Asegúrate de tener este TextField

            databaseService.insertarObservacion(
                    userId,
                    taxonId,
                    new java.util.Date(),
                    latitude,
                    longitude,
                    imageUrl,
                    imageId
            );
            showAlert("Éxito", "Observación insertada correctamente.");
        } catch (Exception e) {
            showAlert("Error", "No se pudo insertar la observación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleInsertIdentification(ActionEvent event) {
        try {
            int observationId = Integer.parseInt(observationIdField.getText());
            int userId = Integer.parseInt(userIdField.getText());
            int taxonId = Integer.parseInt(taxonIdField.getText());
            String identificationLevel = identificationLevelField.getText();

            databaseService.insertarIdentificacion(
                    observationId,
                    userId,
                    taxonId,
                    identificationLevel,
                    new java.util.Date()
            );
            showAlert("Éxito", "Identificación insertada correctamente.");
        } catch (Exception e) {
            showAlert("Error", "No se pudo insertar la identificación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleUpdateImage(ActionEvent event) {
        try {
            int imageId = Integer.parseInt(imageIdField.getText());
            String place = placeField.getText();
            int taxonId = Integer.parseInt(taxonIdField.getText());

            databaseService.actualizarImagen(imageId, place, taxonId);
            showAlert("Éxito", "Imagen actualizada correctamente.");
        } catch (Exception e) {
            showAlert("Error", "No se pudo actualizar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleUpdateObservation(ActionEvent event) {
        try {
            int imageId = Integer.parseInt(imageIdField.getText());
            String place = placeField.getText();
            int taxonId = Integer.parseInt(taxonIdField.getText());

            databaseService.actualizarObservation(imageId, place, taxonId);
            showAlert("Éxito", "Imagen actualizada correctamente.");
        } catch (Exception e) {
            showAlert("Error", "No se pudo actualizar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleUpdateIdentification(ActionEvent event) {
        try {
            int imageId = Integer.parseInt(imageIdField.getText());
            String place = placeField.getText();
            int taxonId = Integer.parseInt(taxonIdField.getText());

            databaseService.actualizarIdentification(imageId, place, taxonId);
            showAlert("Éxito", "Imagen actualizada correctamente.");
        } catch (Exception e) {
            showAlert("Error", "No se pudo actualizar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void handleDeleteRecord(ActionEvent event) {
        try {
            int id = Integer.parseInt(userIdField.getText());
            int opcions = Integer.parseInt(taxonIdField.getText());

            databaseService.eliminarDato(opcions, id);
            showAlert("Éxito", "Registro eliminado correctamente.");
        } catch (Exception e) {
            showAlert("Error", "No se pudo eliminar el registro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleQueryData(ActionEvent event) {
        try {
            List<Object[]> results = queryService.getObservationsWithDetails();
            System.out.println("Número de resultados: " + results.size());

            StringBuilder message = new StringBuilder("Resultados:\n");

            if (results.isEmpty()) {
                resultArea.setText("No se encontraron resultados.");
                return;
            }

            for (Object[] row : results) {
                System.out.println(Arrays.toString(row)); // para depuración
                message.append(String.format(
                        "Reino: %s, Especie: %s, Fecha: %s, Sitio: %s, Usuario: %s, Imagen: %s\n",
                        row[0], row[1], row[2], row[3], row[4], row[5]
                ));
            }

            resultArea.setText(message.toString());

        } catch (Exception e) {
            showAlert("Error", "No se pudo realizar la consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleQueryTaxonomy(ActionEvent event) {
        try {
            List<Object[]> results = queryService.getTaxonomyByObservation();
            System.out.println("Número de resultados: " + results.size());

            StringBuilder message = new StringBuilder("Taxonomía por Observación:\n");

            if (results.isEmpty()) {
                resultArea.setText("No se encontraron resultados.");
                return;
            }

            for (Object[] row : results) {
                System.out.println(Arrays.toString(row));
                message.append(String.format(
                        "ID: %s, Reino: %s, Filo: %s, Clase: %s, Orden: %s, Familia: %s, Género: %s, Especie: %s, Nombre común: %s\n",
                        row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]
                ));
            }

            resultArea.setText(message.toString());

        } catch (Exception e) {
            showAlert("Error", "No se pudo realizar la consulta de taxonomía: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleQueryImageDetails(ActionEvent event) {
        try {
            List<Object[]> results = queryService.getImageDetailsWithTaxonomy();
            System.out.println("Número de resultados: " + results.size());

            StringBuilder message = new StringBuilder("Detalles de Imágenes con Taxonomía:\n");

            if (results.isEmpty()) {
                resultArea.setText("No se encontraron resultados.");
                return;
            }

            for (Object[] row : results) {
                System.out.println(Arrays.toString(row));
                message.append(String.format(
                        "Reino: %s, Especie: %s, Fecha Observación: %s, Lugar: %s, Usuario: %s, Fecha Imagen: %s\n",
                        row[0], row[1], row[2], row[3], row[4], row[5]
                ));
            }

            resultArea.setText(message.toString());

        } catch (Exception e) {
            showAlert("Error", "No se pudo realizar la consulta de imágenes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

