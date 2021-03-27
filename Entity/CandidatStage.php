<?php

namespace App\Entity;

use App\Repository\CandidatStageRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CandidatStageRepository::class)
 */
class CandidatStage
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;


    /**
     * @ORM\Column(type="datetime")
     * @Assert\Range(
     *      min = "now",
     *      minMessage = "Cette valeur doit être le '{{ limit }}' ou plus."
     * )
     */
    private $DateCandidature;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="ce champs est obligatoire")
     * @Assert\Email(
     *     message = "Cette adresse '{{ value }}' invalide."
     * )
     */
    private $Email;

    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="candidatStage")
     * @ORM\JoinColumn(nullable=false)
     */
    
    private $IDEntreprise;

    /**
     * @ORM\ManyToOne(targetEntity=Stagiaire::class, inversedBy="candidatStage")
     * @ORM\JoinColumn(nullable=false)
     */
    private $IDStagiaire;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdStagiaire(): ?Stagiaire
    {
        return $this->IDStagiaire;
    }

    public function setIdStagiaire(?Stagiaire $IdStagiaire): self
    {
        $this->IDStagiaire = $IdStagiaire;

        return $this;
    }

    public function getIdEntreprise(): ?Entreprise
    {
        return $this->IDEntreprise;
    }

    public function setIdEntreprise(?Entreprise $IdEntreprise): self
    {
        $this->IDEntreprise = $IdEntreprise;

        return $this;
    }

    public function getDateCandidature(): ?\DateTimeInterface
    {
        return $this->DateCandidature;
    }

    public function setDateCandidature(\DateTimeInterface $DateCandidature): self
    {
        $this->DateCandidature = $DateCandidature;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->Email;
    }

    public function setEmail(?string $Email): self
    {
        $this->Email = $Email;

        return $this;
    }
}
