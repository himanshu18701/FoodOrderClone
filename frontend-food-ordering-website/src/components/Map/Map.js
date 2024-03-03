import React from 'react';
import { MapContainer, TileLayer, Marker, useMapEvents } from 'react-leaflet';
import L from 'leaflet'; 
import 'leaflet/dist/leaflet.css';
import customMarkerIcon from './custom-marker-icon.png';

const Map = ({ location, onLocationChange }) => {
  
  const defaultCenter = { lat: 28.6139, lng: 77.2090 };

  const LocationMarker = () => {
    useMapEvents({
      click(e) {
        onLocationChange(e.latlng);
      }
    });
    return location ? <Marker position={location} icon={customMarker} /> : null;
  };
  const customMarker = new L.Icon({
    iconUrl: customMarkerIcon,
    iconSize: [32, 32],
    iconAnchor: [16, 32],
  });

  return (
    <MapContainer 
      center={location || defaultCenter} 
      zoom={13} 
      scrollWheelZoom={true}
      style={{ height: '300px', width: '100%' }} 
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      />
      <LocationMarker />
    </MapContainer>
  );
};

export default Map;
