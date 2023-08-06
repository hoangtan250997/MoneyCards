package com.hoangtan.moneycards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class UserRoleAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Role role;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User users;

    @CreationTimestamp
    @Column(name = "assigned_date")
	private LocalDate assignedDate;

	@UpdateTimestamp
	@Column(name = "modified_date")
	private LocalDate updatedDate;
}
