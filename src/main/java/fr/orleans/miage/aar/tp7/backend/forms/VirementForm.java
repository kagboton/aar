package fr.orleans.miage.aar.tp7.backend.forms;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class VirementForm {

    @NotNull
    private long compteSource;
    @NotNull
    private long compteDest;
    @NotNull @Min(1)
    private double montant;

}
