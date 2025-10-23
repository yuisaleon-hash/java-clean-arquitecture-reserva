-- ===============================================================================
-- SCRIPT PARA INSERTAR DATOS DE PRUEBA - CLÍNICA VETERINARIA
-- ===============================================================================

-- Limpiar datos existentes (opcional)
-- DELETE FROM prescriptions;
-- DELETE FROM medical_records;
-- DELETE FROM appointments;
-- DELETE FROM pets;
-- DELETE FROM owners;
-- DELETE FROM veterinarians;
-- DELETE FROM medications;
-- DELETE FROM species;

-- 1. INSERTAR ESPECIES
-- ===============================================================================
INSERT INTO species (id, name, description, created_at) VALUES
                                                            ('550e8400-e29b-41d4-a716-446655440001', 'Dog', 'Domestic dog (Canis lupus familiaris)', NOW()),
                                                            ('550e8400-e29b-41d4-a716-446655440002', 'Cat', 'Domestic cat (Felis catus)', NOW()),
                                                            ('550e8400-e29b-41d4-a716-446655440003', 'Bird', 'Pet birds (various species)', NOW()),
                                                            ('550e8400-e29b-41d4-a716-446655440004', 'Rabbit', 'Domestic rabbit (Oryctolagus cuniculus)', NOW()),
                                                            ('550e8400-e29b-41d4-a716-446655440005', 'Hamster', 'Syrian hamster (Mesocricetus auratus)', NOW());

-- 2. INSERTAR DUEÑOS
-- ===============================================================================
INSERT INTO owners (id, first_name, last_name, email, phone, address, created_at, updated_at) VALUES
                                                                                                  ('650e8400-e29b-41d4-a716-446655440001', 'Juan', 'Pérez', 'juan.perez@email.com', '+51-999-123456', 'Av. Larco 123, Miraflores, Lima', NOW(), NOW()),
                                                                                                  ('650e8400-e29b-41d4-a716-446655440002', 'María', 'García', 'maria.garcia@email.com', '+51-999-654321', 'Jr. Unión 456, Centro de Lima', NOW(), NOW()),
                                                                                                  ('650e8400-e29b-41d4-a716-446655440003', 'Carlos', 'López', 'carlos.lopez@email.com', '+51-999-789012', 'Av. Arequipa 789, San Isidro, Lima', NOW(), NOW()),
                                                                                                  ('650e8400-e29b-41d4-a716-446655440004', 'Ana', 'Martínez', 'ana.martinez@email.com', '+51-999-345678', 'Calle Los Olivos 234, Surco, Lima', NOW(), NOW()),
                                                                                                  ('650e8400-e29b-41d4-a716-446655440005', 'Luis', 'Rodríguez', 'luis.rodriguez@email.com', '+51-999-567890', 'Av. Brasil 567, Breña, Lima', NOW(), NOW());

-- 3. INSERTAR VETERINARIOS
-- ===============================================================================
INSERT INTO veterinarians (id, first_name, last_name, email, phone, license_number, specialization, is_active, created_at, updated_at) VALUES
                                                                                                                                           ('750e8400-e29b-41d4-a716-446655440001', 'Ana', 'Rodríguez', 'ana.rodriguez@veterinaria.com', '+51-999-111222', 'VET-001', 'General Practice', true, NOW(), NOW()),
                                                                                                                                           ('750e8400-e29b-41d4-a716-446655440002', 'Luis', 'Martínez', 'luis.martinez@veterinaria.com', '+51-999-333444', 'VET-002', 'Surgery', true, NOW(), NOW()),
                                                                                                                                           ('750e8400-e29b-41d4-a716-446655440003', 'Carmen', 'Silva', 'carmen.silva@veterinaria.com', '+51-999-555666', 'VET-003', 'Dermatology', true, NOW(), NOW()),
                                                                                                                                           ('750e8400-e29b-41d4-a716-446655440004', 'Roberto', 'Flores', 'roberto.flores@veterinaria.com', '+51-999-777888', 'VET-004', 'Cardiology', true, NOW(), NOW()),
                                                                                                                                           ('750e8400-e29b-41d4-a716-446655440005', 'Patricia', 'Vega', 'patricia.vega@veterinaria.com', '+51-999-999000', 'VET-005', 'Ophthalmology', true, NOW(), NOW());

-- 4. INSERTAR MASCOTAS
-- ===============================================================================
INSERT INTO pets (id, name, species_id, breed, birth_date, weight, color, gender, owner_id, is_active, created_at, updated_at) VALUES
-- Perros
('850e8400-e29b-41d4-a716-446655440001', 'Buddy', '550e8400-e29b-41d4-a716-446655440001', 'Golden Retriever', '2020-03-15', 28.50, 'Dorado', 'MALE', '650e8400-e29b-41d4-a716-446655440001', true, NOW(), NOW()),
('850e8400-e29b-41d4-a716-446655440002', 'Luna', '550e8400-e29b-41d4-a716-446655440001', 'Labrador', '2019-07-22', 32.00, 'Negro', 'FEMALE', '650e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
('850e8400-e29b-41d4-a716-446655440003', 'Rocky', '550e8400-e29b-41d4-a716-446655440001', 'Pastor Alemán', '2021-01-10', 35.75, 'Marrón y Negro', 'MALE', '650e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),

-- Gatos
('850e8400-e29b-41d4-a716-446655440004', 'Whiskers', '550e8400-e29b-41d4-a716-446655440002', 'Persa', '2020-09-05', 4.20, 'Blanco', 'MALE', '650e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
('850e8400-e29b-41d4-a716-446655440005', 'Mimi', '550e8400-e29b-41d4-a716-446655440002', 'Siamés', '2021-05-18', 3.80, 'Crema y Marrón', 'FEMALE', '650e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
('850e8400-e29b-41d4-a716-446655440006', 'Shadow', '550e8400-e29b-41d4-a716-446655440002', 'Doméstico', '2019-12-25', 5.10, 'Negro', 'MALE', '650e8400-e29b-41d4-a716-446655440005', true, NOW(), NOW()),

-- Aves
('850e8400-e29b-41d4-a716-446655440007', 'Pico', '550e8400-e29b-41d4-a716-446655440003', 'Canario', '2022-02-14', 0.02, 'Amarillo', 'MALE', '650e8400-e29b-41d4-a716-446655440001', true, NOW(), NOW()),
('850e8400-e29b-41d4-a716-446655440008', 'Kiwi', '550e8400-e29b-41d4-a716-446655440003', 'Periquito', '2021-11-30', 0.03, 'Verde', 'FEMALE', '650e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),

-- Conejos
('850e8400-e29b-41d4-a716-446655440009', 'Fluffy', '550e8400-e29b-41d4-a716-446655440004', 'Holandés', '2021-08-12', 2.80, 'Blanco y Negro', 'FEMALE', '650e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
('850e8400-e29b-41d4-a716-446655440010', 'Bunny', '550e8400-e29b-41d4-a716-446655440004', 'Angora', '2020-06-20', 3.20, 'Gris', 'MALE', '650e8400-e29b-41d4-a716-446655440005', true, NOW(), NOW());

-- 5. INSERTAR CITAS (FUTURAS Y PASADAS)
-- ===============================================================================
INSERT INTO appointments (id, pet_id, veterinarian_id, appointment_date, duration_minutes, reason, status, notes, created_at, updated_at) VALUES
-- Citas pasadas (completadas)
('950e8400-e29b-41d4-a716-446655440001', '850e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440001', '2025-10-15 09:00:00', 30, 'Consulta general', 'COMPLETED', 'Chequeo rutinario completado', NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440002', '850e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440003', '2025-10-18 14:30:00', 45, 'Problema de piel', 'COMPLETED', 'Tratamiento para dermatitis', NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440003', '850e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440002', '2025-10-20 11:00:00', 60, 'Cirugía menor', 'COMPLETED', 'Extracción de quiste', NOW(), NOW()),

-- Citas futuras (programadas)
('950e8400-e29b-41d4-a716-446655440004', '850e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440001', '2025-10-25 10:00:00', 30, 'Vacunación anual', 'SCHEDULED', NULL, NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440005', '850e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440003', '2025-10-26 15:30:00', 30, 'Chequeo de rutina', 'CONFIRMED', NULL, NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440006', '850e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440004', '2025-10-28 09:30:00', 45, 'Examen cardiológico', 'SCHEDULED', NULL, NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440007', '850e8400-e29b-41d4-a716-446655440007', '750e8400-e29b-41d4-a716-446655440005', '2025-10-30 16:00:00', 20, 'Revisión ocular', 'SCHEDULED', NULL, NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440008', '850e8400-e29b-41d4-a716-446655440008', '750e8400-e29b-41d4-a716-446655440001', '2025-11-02 11:30:00', 25, 'Consulta preventiva', 'SCHEDULED', NULL, NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440009', '850e8400-e29b-41d4-a716-446655440009', '750e8400-e29b-41d4-a716-446655440002', '2025-11-05 14:00:00', 30, 'Esterilización', 'CONFIRMED', NULL, NOW(), NOW()),
('950e8400-e29b-41d4-a716-446655440010', '850e8400-e29b-41d4-a716-446655440010', '750e8400-e29b-41d4-a716-446655440001', '2025-11-08 10:15:00', 35, 'Vacunación', 'SCHEDULED', NULL, NOW(), NOW());

-- 6. INSERTAR MEDICAMENTOS
-- ===============================================================================
INSERT INTO medications (id, name, description, manufacturer, unit_type, is_active, created_at) VALUES
                                                                                                    ('a50e8400-e29b-41d4-a716-446655440001', 'Amoxicilina', 'Antibiótico de amplio espectro para uso veterinario', 'VetPharma', 'mg', true, NOW()),
                                                                                                    ('a50e8400-e29b-41d4-a716-446655440002', 'Ibuprofeno Veterinario', 'Antiinflamatorio no esteroideo para animales', 'AnimalMed', 'mg', true, NOW()),
                                                                                                    ('a50e8400-e29b-41d4-a716-446655440003', 'Vitamina B Complex', 'Complejo vitamínico para mascotas', 'NutriPet', 'ml', true, NOW()),
                                                                                                    ('a50e8400-e29b-41d4-a716-446655440004', 'Metacam', 'Antiinflamatorio para perros y gatos', 'Boehringer', 'ml', true, NOW()),
                                                                                                    ('a50e8400-e29b-41d4-a716-446655440005', 'Heartgard', 'Preventivo contra parásitos cardíacos', 'Merial', 'tablets', true, NOW());

-- 7. INSERTAR HISTORIALES MÉDICOS
-- ===============================================================================
INSERT INTO medical_records (id, pet_id, veterinarian_id, appointment_id, visit_date, diagnosis, treatment, symptoms, weight, temperature, notes, created_at, updated_at) VALUES
                                                                                                                                                                              ('b50e8400-e29b-41d4-a716-446655440001', '850e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440001', '950e8400-e29b-41d4-a716-446655440001', '2025-10-15 09:00:00', 'Estado de salud general excelente', 'Mantenimiento preventivo', 'Sin síntomas', 28.50, 38.5, 'Perro en excelente condición física', NOW(), NOW()),
                                                                                                                                                                              ('b50e8400-e29b-41d4-a716-446655440002', '850e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440003', '950e8400-e29b-41d4-a716-446655440002', '2025-10-18 14:30:00', 'Dermatitis alérgica', 'Tratamiento tópico y antihistamínicos', 'Picazón, enrojecimiento en piel', 4.20, 38.8, 'Respuesta positiva al tratamiento', NOW(), NOW()),
                                                                                                                                                                              ('b50e8400-e29b-41d4-a716-446655440003', '850e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440002', '950e8400-e29b-41d4-a716-446655440003', '2025-10-20 11:00:00', 'Quiste sebáceo', 'Extracción quirúrgica menor', 'Bulto en el lomo', 32.00, 38.3, 'Cirugía exitosa, recuperación normal', NOW(), NOW());

-- 8. INSERTAR PRESCRIPCIONES
-- ===============================================================================
INSERT INTO prescriptions (id, medical_record_id, medication_id, dosage, frequency, duration_days, instructions, created_at) VALUES
                                                                                                                                 ('c50e8400-e29b-41d4-a716-446655440001', 'b50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440001', '250mg', 'Cada 12 horas', 7, 'Administrar con comida para evitar malestar estomacal', NOW()),
                                                                                                                                 ('c50e8400-e29b-41d4-a716-446655440002', 'b50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440004', '0.5ml', 'Una vez al día', 5, 'Aplicar directamente en la zona afectada', NOW()),
                                                                                                                                 ('c50e8400-e29b-41d4-a716-446655440003', 'b50e8400-e29b-41d4-a716-446655440003', 'a50e8400-e29b-41d4-a716-446655440002', '100mg', 'Cada 8 horas', 3, 'Solo para manejo del dolor post-operatorio', NOW());

-- ===============================================================================
-- CONSULTAS DE VERIFICACIÓN
-- ===============================================================================

-- Verificar datos insertados
SELECT 'ESPECIES' as tabla, COUNT(*) as registros FROM species
UNION ALL
SELECT 'DUEÑOS' as tabla, COUNT(*) as registros FROM owners
UNION ALL
SELECT 'VETERINARIOS' as tabla, COUNT(*) as registros FROM veterinarians
UNION ALL
SELECT 'MASCOTAS' as tabla, COUNT(*) as registros FROM pets
UNION ALL
SELECT 'CITAS' as tabla, COUNT(*) as registros FROM appointments
UNION ALL
SELECT 'MEDICAMENTOS' as tabla, COUNT(*) as registros FROM medications
UNION ALL
SELECT 'HIST. MÉDICOS' as tabla, COUNT(*) as registros FROM medical_records
UNION ALL
SELECT 'PRESCRIPCIONES' as tabla, COUNT(*) as registros FROM prescriptions;

-- Consulta de ejemplo: Mascotas con sus dueños
SELECT
    p.name as mascota,
    s.name as especie,
    p.breed as raza,
    o.first_name || ' ' || o.last_name as dueño,
    o.email
FROM pets p
         JOIN species s ON p.species_id = s.id
         JOIN owners o ON p.owner_id = o.id
ORDER BY s.name, p.name;

-- Consulta de ejemplo: Citas programadas (futuras)
SELECT
    a.appointment_date as fecha,
    p.name as mascota,
    o.first_name || ' ' || o.last_name as dueño,
    v.first_name || ' ' || v.last_name as veterinario,
    a.reason as motivo,
    a.status as estado
FROM appointments a
         JOIN pets p ON a.pet_id = p.id
         JOIN owners o ON p.owner_id = o.id
         JOIN veterinarians v ON a.veterinarian_id = v.id
WHERE a.appointment_date > NOW()
ORDER BY a.appointment_date;

-- ===============================================================================
-- DATOS ÚTILES PARA TESTING
-- ===============================================================================

/*
IDs ÚTILES PARA TESTING DE API:

DUEÑOS:
- Juan Pérez: 650e8400-e29b-41d4-a716-446655440001
- María García: 650e8400-e29b-41d4-a716-446655440002

VETERINARIOS:
- Dra. Ana Rodríguez: 750e8400-e29b-41d4-a716-446655440001
- Dr. Luis Martínez: 750e8400-e29b-41d4-a716-446655440002

MASCOTAS:
- Buddy (Golden Retriever): 850e8400-e29b-41d4-a716-446655440001
- Luna (Labrador): 850e8400-e29b-41d4-a716-446655440002
- Whiskers (Persa): 850e8400-e29b-41d4-a716-446655440004

ESPECIES:
- Perro: 550e8400-e29b-41d4-a716-446655440001
- Gato: 550e8400-e29b-41d4-a716-446655440002

EJEMPLO DE CURL PARA CREAR CITA:
curl -X POST http://localhost:8000/api/v1/appointments \
  -H "Content-Type: application/json" \
  -d '{
    "petId": "850e8400-e29b-41d4-a716-446655440001",
    "veterinarianId": "750e8400-e29b-41d4-a716-446655440001",
    "appointmentDate": "2025-11-15T10:00:00",
    "durationMinutes": 30,
    "reason": "Consulta general"
  }'

EJEMPLO DE CURL PARA BUSCAR CITAS:
curl "http://localhost:8000/api/v1/appointments?date=2025-10-25"
*/