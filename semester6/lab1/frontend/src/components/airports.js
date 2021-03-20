
import React from 'react'

const Airports = ({ airports }) => {
  return (
    <div>
      <center><h1>Airports List</h1></center>
      {airports.map((airport) => (
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">{airport.name}</h5>
            <h6 class="card-subtitle mb-2 text-muted">{airport.city}</h6>
            <p class="card-text">{airport.country}</p>
          </div>
        </div>
      ))}
    </div>
  )
};

export default Airports