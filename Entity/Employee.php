<?php

namespace App\Entity;

use App\Repository\EmployeeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use app\Entity\Rec1;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=EmployeeRepository::class)
 */
class Employee extends User
{


    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @Groups({"employee"})
     */
    private $diplome;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @Groups({"employee"})
     */
    private $entreprise;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     *   *  @Groups({"employee"})
     */
    private $typedecontrat;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     *   *  @Groups({"employee"})
     */
    private $posteoccupe;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     *   *  @Groups({"employee"})
     */
    private $date;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @Groups({"employee"})
     */
    private $competence;

    /**
     * @ORM\OneToMany(targetEntity=Reclamation::class, mappedBy="employee", orphanRemoval=true)

     */

    /**
     * @ORM\OneToMany(targetEntity=Rec2::class, mappedBy="employee")
     */
    private $rec2s;




    public function __construct()
    {
        $this->rec1 = new ArrayCollection();
        $this->rec2s = new ArrayCollection();
    }



    public function getDiplome(): ?string
    {
        return $this->diplome;
    }

    public function setDiplome(string $diplome): self
    {
        $this->diplome = $diplome;

        return $this;
    }

    public function getEntreprise(): ?string
    {
        return $this->entreprise;
    }

    public function setEntreprise(string $entreprise): self
    {
        $this->entreprise = $entreprise;

        return $this;
    }

    public function getTypedecontrat(): ?string
    {
        return $this->typedecontrat;
    }

    public function setTypedecontrat(string $typedecontrat): self
    {
        $this->typedecontrat = $typedecontrat;

        return $this;
    }

    public function getPosteoccupe(): ?string
    {
        return $this->posteoccupe;
    }

    public function setPosteoccupe(string $posteoccupe): self
    {
        $this->posteoccupe = $posteoccupe;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getCompetence(): ?string
    {
        return $this->competence;
    }

    public function setCompetence(string $competence): self
    {
        $this->competence = $competence;

        return $this;
    }


    /**
     * @return Collection|Rec2[]
     */
    public function getRec2s(): Collection
    {
        return $this->rec2s;
    }

    public function addRec2(Rec2 $rec2): self
    {
        if (!$this->rec2s->contains($rec2)) {
            $this->rec2s[] = $rec2;
            $rec2->setEmployee($this);
        }

        return $this;
    }

    public function removeRec2(Rec2 $rec2): self
    {
        if ($this->rec2s->removeElement($rec2)) {
            // set the owning side to null (unless already changed)
            if ($rec2->getEmployee() === $this) {
                $rec2->setEmployee(null);
            }
        }

        return $this;
    }
    public function __toString() {
        return (string) $this->getId();
    }


}
