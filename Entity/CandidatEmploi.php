<?php

namespace App\Entity;

use App\Repository\CandidatEmploiRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;



/**
 * @ORM\Entity(repositoryClass=CandidatEmploiRepository::class)
 */
class CandidatEmploi
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
     *      minMessage = "Cette valeur doit être le  {{ limit }} ou plus."
     * )
     */
    private $DateCandiature;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Email(message="email invalide")
     * @Assert\NotBlank(message="email obligatoire")
     */
    private $email;

    /**
     * @ORM\ManyToOne(targetEntity=Employe::class, inversedBy="candidatEmploi",)
     * @Assert\NotBlank(message="IdEmploye obligatoire")
     * @ORM\JoinColumn(nullable=false)
     */
    private $IdEmploye;
    
    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="candidatEmploi")
     * @ORM\JoinColumn(nullable=false)
     */
    
    private $IDEntreprise;


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdEmploye(): ?Employe
    {
        return $this->IdEmploye;
    }

    public function setIdEmploye(?Employe $IdEmploye): self
    {
        $this->IdEmploye = $IdEmploye;

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

    public function getDateCandiature(): ?\DateTimeInterface
    {
        return $this->DateCandiature;
    }

    public function setDateCandiature(\DateTimeInterface $DateCandiature): self
    {
        $this->DateCandiature = $DateCandiature;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(?string $email): self
    {
        $this->email = $email;

        return $this;
    }
}
