package hr.fer.zemris.java.tecaj_13.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Blog Comment is a model class for blog comments, comment is 
 * connected with a blogEntry that it belongs to.
 * 
 * @author Ante Spajic
 */
@Entity
@Table(name="blog_comments")
public class BlogComment {

	/** The comment id. */
	@Id @GeneratedValue
	private Long id;
	
	/** The blog entry that this comment is associated with. */
	@ManyToOne
	@JoinColumn(nullable=false)
	private BlogEntry blogEntry;

	/** The commentators  e-mail. */
	@Column(length=100, nullable=false)
	private String usersEMail;
	
	/** Comment message. */
	@Column(length=4096, nullable=false)
	private String message;
	
	/** The posted on property. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date postedOn;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the blog entry.
	 *
	 * @return the blog entry
	 */
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}
	
	/**
	 * Sets the blog entry.
	 *
	 * @param blogEntry the new blog entry
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

	/**
	 * Gets the users e mail.
	 *
	 * @return the users e mail
	 */
	public String getUsersEMail() {
		return usersEMail;
	}

	/**
	 * Sets the users e mail.
	 *
	 * @param usersEMail the new users e mail
	 */
	public void setUsersEMail(String usersEMail) {
		this.usersEMail = usersEMail;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the posted on.
	 *
	 * @return the posted on
	 */
	public Date getPostedOn() {
		return postedOn;
	}

	/**
	 * Sets the posted on.
	 *
	 * @param postedOn the new posted on
	 */
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogComment other = (BlogComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}