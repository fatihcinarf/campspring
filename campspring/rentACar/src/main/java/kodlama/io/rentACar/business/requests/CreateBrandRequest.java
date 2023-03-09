package kodlama.io.rentACar.business.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Bir veri gönderdiğimizde REQUEST
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
	@NotBlank
	@NotNull
	@Size(min = 3,max = 20)
	private String name;
}
