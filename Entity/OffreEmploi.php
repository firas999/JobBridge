<?php

namespace App\Entity;

use App\Repository\OffreEmploiRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;


/**
 * @ORM\Entity(repositoryClass=OffreEmploiRepository::class)
 */
class OffreEmploi
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Type obligatoire")

     */
    private $Type;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Experience obligatoire")
     */
    private $Experience;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Exigence obligatoire")
     */
    private $Exigence;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Poste obligatoire")
     */
    private $Poste;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="Salaire obligatoire")
     * @Assert\Positive(message="Salaire doit etre un nombre positive")
     */
    private $Salaire;

    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="OffresEmploi")
     * @Assert\NotBlank(message="entreprise obligatoire")
     * @ORM\JoinColumn(nullable=false)
     */
    private $entreprise;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $likeC;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $dislikeC;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->Type;
    }

    public function setType(?string $Type): self
    {
        $this->Type = $Type;

        return $this;
    }

    public function getExperience(): ?string
    {
        return $this->Experience;
    }

    public function setExperience(?string $Experience): self
    {
        $this->Experience = $Experience;

        return $this;
    }

    public function getExigence(): ?string
    {
        return $this->Exigence;
    }

    public function setExigence(?string $Exigence): self
    {
        $this->Exigence = $Exigence;

        return $this;
    }

    public function getPoste(): ?string
    {
        return $this->Poste;
    }

    public function setPoste(?string $Poste): self
    {
        $this->Poste = $Poste;

        return $this;
    }

    public function getSalaire(): ?int
    {
        return $this->Salaire;
    }

    public function setSalaire(?int $Salaire): self
    {
        $this->Salaire = $Salaire;

        return $this;
    }

    public function getEntreprise(): ?Entreprise
    {
        return $this->entreprise;
    }

    public function setEntreprise(?Entreprise $entreprise): self
    {
        $this->entreprise = $entreprise;

        return $this;
    }

    public function getLikeC(): ?int
    {
        return $this->likeC;
    }

    public function setLikeC(?int $likeC): self
    {
        $this->likeC = $likeC;

        return $this;
    }

    public function getDislikeC(): ?int
    {
        return $this->dislikeC;
    }

    public function setDislikeC(int $dislikeC): self
    {
        $this->dislikeC = $dislikeC;

        return $this;
    }
}
