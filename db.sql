-- Clínica Veterinaria - Script de Base de Datos PostgreSQL
-- Clean Architecture Example

-- Crear base de datos
-- CREATE DATABASE veterinary_clinic;

-- Usar la base de datos
-- \c veterinary_clinic;

-- Extensiones necesarias
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabla de dueños de mascotas
CREATE TABLE owners (
                        id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        phone VARCHAR(20),
                        address TEXT,
                        created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de especies
CREATE TABLE species (
                         id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         name VARCHAR(50) NOT NULL UNIQUE,
                         description TEXT,
                         created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de mascotas
CREATE TABLE pets (
                      id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                      name VARCHAR(100) NOT NULL,
                      species_id UUID NOT NULL REFERENCES species(id),
                      breed VARCHAR(100),
                      birth_date DATE,
                      weight DECIMAL(5,2),
                      color VARCHAR(50),
                      gender VARCHAR(10) CHECK (gender IN ('MALE', 'FEMALE', 'UNKNOWN')),
                      owner_id UUID NOT NULL REFERENCES owners(id),
                      is_active BOOLEAN DEFAULT true,
                      created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de veterinarios
CREATE TABLE veterinarians (
                               id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                               first_name VARCHAR(100) NOT NULL,
                               last_name VARCHAR(100) NOT NULL,
                               email VARCHAR(255) UNIQUE NOT NULL,
                               phone VARCHAR(20),
                               license_number VARCHAR(50) UNIQUE NOT NULL,
                               specialization VARCHAR(100),
                               is_active BOOLEAN DEFAULT true,
                               created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de citas
CREATE TABLE appointments (
                              id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              pet_id UUID NOT NULL REFERENCES pets(id),
                              veterinarian_id UUID NOT NULL REFERENCES veterinarians(id),
                              appointment_date TIMESTAMP WITH TIME ZONE NOT NULL,
                              duration_minutes INTEGER DEFAULT 30,
                              reason TEXT NOT NULL,
                              status VARCHAR(20) DEFAULT 'SCHEDULED' CHECK (status IN ('SCHEDULED', 'CONFIRMED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
                              notes TEXT,
                              created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de historiales médicos
CREATE TABLE medical_records (
                                 id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                 pet_id UUID NOT NULL REFERENCES pets(id),
                                 veterinarian_id UUID NOT NULL REFERENCES veterinarians(id),
                                 appointment_id UUID REFERENCES appointments(id),
                                 visit_date TIMESTAMP WITH TIME ZONE NOT NULL,
                                 diagnosis TEXT NOT NULL,
                                 treatment TEXT,
                                 symptoms TEXT,
                                 weight DECIMAL(5,2),
                                 temperature DECIMAL(4,2),
                                 notes TEXT,
                                 created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de medicamentos
CREATE TABLE medications (
                             id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                             name VARCHAR(200) NOT NULL,
                             description TEXT,
                             manufacturer VARCHAR(100),
                             unit_type VARCHAR(50), -- mg, ml, tablets, etc.
                             is_active BOOLEAN DEFAULT true,
                             created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de prescripciones
CREATE TABLE prescriptions (
                               id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                               medical_record_id UUID NOT NULL REFERENCES medical_records(id),
                               medication_id UUID NOT NULL REFERENCES medications(id),
                               dosage VARCHAR(100) NOT NULL,
                               frequency VARCHAR(100) NOT NULL,
                               duration_days INTEGER,
                               instructions TEXT,
                               created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Índices para mejorar rendimiento
CREATE INDEX idx_pets_owner_id ON pets(owner_id);
CREATE INDEX idx_pets_species_id ON pets(species_id);
CREATE INDEX idx_appointments_pet_id ON appointments(pet_id);
CREATE INDEX idx_appointments_veterinarian_id ON appointments(veterinarian_id);
CREATE INDEX idx_appointments_date ON appointments(appointment_date);
CREATE INDEX idx_medical_records_pet_id ON medical_records(pet_id);
CREATE INDEX idx_medical_records_veterinarian_id ON medical_records(veterinarian_id);
CREATE INDEX idx_prescriptions_medical_record_id ON prescriptions(medical_record_id);

-- Función para actualizar timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

-- Triggers para actualizar updated_at automáticamente
CREATE TRIGGER update_owners_updated_at BEFORE UPDATE ON owners
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_pets_updated_at BEFORE UPDATE ON pets
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_veterinarians_updated_at BEFORE UPDATE ON veterinarians
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_appointments_updated_at BEFORE UPDATE ON appointments
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_medical_records_updated_at BEFORE UPDATE ON medical_records
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- Datos de ejemplo
INSERT INTO species (name, description) VALUES
                                            ('Dog', 'Domestic dog'),
                                            ('Cat', 'Domestic cat'),
                                            ('Bird', 'Pet birds'),
                                            ('Rabbit', 'Domestic rabbit'),
                                            ('Hamster', 'Small rodent');

INSERT INTO owners (first_name, last_name, email, phone, address) VALUES
                                                                      ('Juan', 'Pérez', 'juan.perez@email.com', '+51-999-123456', 'Av. Larco 123, Miraflores, Lima'),
                                                                      ('María', 'García', 'maria.garcia@email.com', '+51-999-654321', 'Jr. Unión 456, Centro de Lima'),
                                                                      ('Carlos', 'López', 'carlos.lopez@email.com', '+51-999-789012', 'Av. Arequipa 789, San Isidro, Lima');

INSERT INTO veterinarians (first_name, last_name, email, phone, license_number, specialization) VALUES
                                                                                                    ('Dr. Ana', 'Rodríguez', 'ana.rodriguez@veterinaria.com', '+51-999-111222', 'VET-001', 'General Practice'),
                                                                                                    ('Dr. Luis', 'Martínez', 'luis.martinez@veterinaria.com', '+51-999-333444', 'VET-002', 'Surgery'),
                                                                                                    ('Dr. Carmen', 'Silva', 'carmen.silva@veterinaria.com', '+51-999-555666', 'VET-003', 'Dermatology');

INSERT INTO medications (name, description, manufacturer, unit_type) VALUES
                                                                         ('Amoxicilina', 'Antibiótico de amplio espectro', 'VetPharma', 'mg'),
                                                                         ('Ibuprofeno Veterinario', 'Antiinflamatorio no esteroideo', 'AnimalMed', 'mg'),
                                                                         ('Vitamina B Complex', 'Complejo vitamínico', 'NutriPet', 'ml');