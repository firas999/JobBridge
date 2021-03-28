<?php

namespace App\Entity;

use App\Repository\CandidatRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * @ORM\Entity(repositoryClass=CandidatRepository::class)
 */
class Candidat extends User
{


    /**
     * @ORM\Column(type="string", length=255)
      * @Assert\NotBlank(message="Ce champs est obligatoire")
     *  @Groups({"candidat"})
     */
    private $secteur;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     *  @Groups({"candidat"})
     */
    private $siteweb;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     *  @Groups({"candidat"})
     */
    private $tailleentreprise;

    /**
     * @ORM\Column(type="integer")
      * @Assert\NotBlank(message="Cette valeur  est obligatoire")
     *  @Groups({"candidat"})
     */
    private $numerotelephone;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Groups({"candidat"})
     */
    private $type;


    public function getSecteur(): ?string
    {
        return $this->secteur;
    }

    public function setSecteur(string $secteur): self
    {
        $this->secteur = $secteur;

        return $this;
    }

    public function getSiteweb(): ?string
    {
        return $this->siteweb;
    }

    public function setSiteweb(?string $siteweb): self
    {
        $this->siteweb = $siteweb;

        return $this;
    }

    public function getTailleentreprise(): ?int
    {
        return $this->tailleentreprise;
    }

    public function setTailleentreprise(int $tailleentreprise): self
    {
        $this->tailleentreprise = $tailleentreprise;

        return $this;
    }

    public function getNumerotelephone(): ?int
    {
        return $this->numerotelephone;
    }

    public function setNumerotelephone(int $numerotelephone): self
    {
        $this->numerotelephone = $numerotelephone;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }
}
