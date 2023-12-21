package RR;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MobileApplication 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String developer;
	private String version;
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getDeveloper() 
	{
		return developer;
	}
	public void setDeveloper(String developer) 
	{
		this.developer = developer;
	}
	public String getVersion() 
	{
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() 
	{
		return "MobileApplication [id=" + id + ", name=" + name + ", developer=" + developer + ", version=" + version
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getDeveloper()=" + getDeveloper()
				+ ", getVersion()=" + getVersion() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public MobileApplication() 
	{
		super();
	}
}
