package cl.duoc.yuyitos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="SEQUENCE_ROL_ASIGNADO_GENERATOR", sequenceName="SEQUENCE_ROL_ASIGNADO", initialValue=1, allocationSize=1)
@Table (name = "rol_asignado")
public class RolAsignado {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_ROL_ASIGNADO_GENERATOR")
	@Column(name ="rol_id_rol")
	private Long idRolAsignado;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id_persona", referencedColumnName = "id_persona")
    private Persona persona;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asignado", referencedColumnName = "id_rol")
    private Rol rol; 


	public RolAsignado() {
	}

	public RolAsignado(Long idRolAsignado, Persona persona, Rol rol) {
		this.idRolAsignado = idRolAsignado;
		this.persona = persona;
		this.rol = rol;
	}


	public Long getIdRolAsignado() {
		return idRolAsignado;
	}


	public void setIdRolAsignado(Long idRolAsignado) {
		this.idRolAsignado = idRolAsignado;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}

}