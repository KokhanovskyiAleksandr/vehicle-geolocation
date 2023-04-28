CREATE TABLE vehicle_coordinates
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id BIGINT,
    coordinates   POLYGON NOT NULL,
    SPATIAL INDEX (coordinates),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);
