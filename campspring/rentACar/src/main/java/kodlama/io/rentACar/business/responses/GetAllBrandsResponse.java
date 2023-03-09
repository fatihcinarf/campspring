package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//bir veri istediğimizde RESPONSE
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBrandsResponse {
	private int brandId;
	private String brandName;
}
